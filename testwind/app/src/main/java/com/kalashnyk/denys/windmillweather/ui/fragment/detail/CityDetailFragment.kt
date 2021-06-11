package com.kalashnyk.denys.windmillweather.ui.fragment.detail

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.windmillweather.R
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.base.ui.BasePagingFragment
import com.kalashnyk.denys.windmillweather.databinding.CityDetailDataBinding
import com.kalashnyk.denys.windmillweather.di.component.ViewModelComponent
import com.kalashnyk.denys.windmillweather.domain.forecast.ForecastViewModel
import com.kalashnyk.denys.windmillweather.ui.activity.main.MainActivity
import com.kalashnyk.denys.windmillweather.utils.multistate.MultiStateView
import com.kalashnyk.denys.windmillweather.utils.settings.FIRST_LIST_POSITION
import com.kalashnyk.denys.windmillweather.utils.settings.MIN_LIST_SIZE
import javax.inject.Inject

class CityDetailFragment : BasePagingFragment<CityDetailDataBinding>() {

    lateinit var viewModel: ForecastViewModel @Inject set

    override fun getLayoutId(): Int = R.layout.fragment_city_detail

    override fun getListView(): RecyclerView = viewBinder.multiStateView.listView

    override fun getRefreshView(): SwipeRefreshLayout = viewBinder.swipeRefresh

    override fun getStateView(): MultiStateView = viewBinder.multiStateView.multiState

    override fun setupViewLogic(binder: CityDetailDataBinding) {
        setupAppBar(getParentActivity().currentCity?.name ?: getString(R.string.title_detail), true)
        getParentActivity().currentCity?.let { city ->
            binder.city = city
            viewModel.setForecastCity(city)
            viewModel.fetchData()
        }
        binder.swipeRefresh.setOnRefreshListener {
            viewModel.setRefreshing(true)
            viewModel.fetchData()
        }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun initListView() {
        getListView().layoutManager = GridLayoutManager(requireContext(), 2)
        getListView().adapter = pagingAdapter.apply {
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
    }

    override fun removeObserver() {
        viewModel.getPagedList().removeObservers(viewLifecycleOwner)
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

    companion object {
        @JvmStatic
        fun newInstance() = CityDetailFragment()
    }
}
