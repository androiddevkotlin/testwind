package com.kalashnyk.denys.windmillweather.ui.fragment.cities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.windmillweather.R
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.base.ui.BasePagingFragment
import com.kalashnyk.denys.windmillweather.databinding.CitiesDataBinding
import com.kalashnyk.denys.windmillweather.di.component.ViewModelComponent
import com.kalashnyk.denys.windmillweather.domain.cities.CitiesViewModel
import com.kalashnyk.denys.windmillweather.ui.activity.main.MainActivity
import com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding.AddCityCallback
import com.kalashnyk.denys.windmillweather.ui.fragment.cities.binding.CitiesActionListener
import com.kalashnyk.denys.windmillweather.ui.navigator.model.Pages
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.utils.error.ErrorParser
import com.kalashnyk.denys.windmillweather.utils.extensions.showAddCityDialog
import com.kalashnyk.denys.windmillweather.utils.multistate.MultiStateView
import com.kalashnyk.denys.windmillweather.utils.settings.FIRST_LIST_POSITION
import com.kalashnyk.denys.windmillweather.utils.settings.MIN_LIST_SIZE
import com.kalashnyk.denys.windmillweather.utils.permission.PermissionHelper.checkLocationPermission
import io.reactivex.functions.Consumer
import javax.inject.Inject

class CitiesFragment : BasePagingFragment<CitiesDataBinding>(),
    AddCityCallback, CitiesActionListener, LocationListener {

    lateinit var viewModel: CitiesViewModel @Inject set

    lateinit var locationManager: LocationManager

    override fun getLayoutId(): Int = R.layout.fragment_cities

    override fun getListView(): RecyclerView = viewBinder.multiStateView.listView

    override fun getRefreshView(): SwipeRefreshLayout = viewBinder.swipeRefresh

    override fun getStateView(): MultiStateView = viewBinder.multiStateView.multiState

    override fun setupViewLogic(binder: CitiesDataBinding) {
        checkLocationPermission(getParentActivity(), Consumer { isGranted: Boolean? ->
            isGranted?.let {
                if (it) {
                    locationManager = (getParentActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager).apply {
                        if (ContextCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                0L,
                                0F,
                                this@CitiesFragment
                            )
                        }
                    }
                } else {
                    showSnack(getString(R.string.error_no_permission_location))
                }
            }
        })
        setupAppBar(R.string.title_cities_available)
        binder.btnAddCity.setOnClickListener {
            context?.showAddCityDialog(this)
        }
        binder.swipeRefresh.setOnRefreshListener {
            viewModel.setRefreshing(true)
            viewModel.fetchData()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }

    override fun addCity(name: String) {
        viewModel.addCity(name)
    }

    override fun deleteCity(city: CityEntity) {
        viewModel.deleteCity(city.id)
    }

    override fun showCityDetail(city: CityEntity) {
        getParentActivity().openPage(Pages.CITY_DETAIL)
        getParentActivity().currentCity = city
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun initListView() {
        getListView().layoutManager = GridLayoutManager(requireContext(), 2)
        getListView().adapter = pagingAdapter.apply {
            setCitiesActionListener(this@CitiesFragment)
            registerAdapterDataObserver(object  : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (positionStart == FIRST_LIST_POSITION && itemCount == MIN_LIST_SIZE) {
                        getListView().scrollToPosition(FIRST_LIST_POSITION)
                    }
                }
            })
        }
        viewModel.getRefreshing().let {
            viewBinder.refreshing = it
        }
        viewModel.isLoading().let {
            viewBinder.loading = it
        }
    }

    override fun initObserver() {
        viewModel.initLiveData( this)
        viewModel.getPagedList().observe(viewLifecycleOwner, Observer { onItemsLoaded(it) })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            showSnack(ErrorParser(requireContext()).parse(it))
        })
    }

    override fun removeObserver() {
        viewModel.getPagedList().removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
    }

    override fun onItemsLoaded(item: PagedList<BaseCardModel>?) {
        if (item.isNullOrEmpty()) {
            getStateView().viewState = MultiStateView.VIEW_STATE_EMPTY
        } else {
            pagingAdapter.submitList(item)
            getStateView().viewState = MultiStateView.VIEW_STATE_CONTENT
        }
    }

    override fun displayProgress() {
        getStateView().viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun onLoadError(errorMessage: String) {
        getStateView().viewState = MultiStateView.VIEW_STATE_ERROR
        showSnack(errorMessage)
    }

    override fun getParentActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            viewModel.addCurrentCity(Pair(it.latitude, it.longitude))
            locationManager.removeUpdates(this)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) { }

    override fun onProviderEnabled(provider: String?) { }

    override fun onProviderDisabled(provider: String?) { }

    companion object {
        @JvmStatic
        fun newInstance() = CitiesFragment()
    }
}
