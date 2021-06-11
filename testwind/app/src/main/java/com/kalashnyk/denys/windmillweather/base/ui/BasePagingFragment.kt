package com.kalashnyk.denys.windmillweather.base.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kalashnyk.denys.windmillweather.WindmillWeatherApp
import com.kalashnyk.denys.windmillweather.base.model.BaseCardModel
import com.kalashnyk.denys.windmillweather.di.component.ViewModelComponent
import com.kalashnyk.denys.windmillweather.ui.navigator.model.Pages
import com.kalashnyk.denys.windmillweather.utils.multistate.MultiStateView
import com.kalashnyk.denys.windmillweather.utils.paging.DiffCallbackBaseCardModel
import com.kalashnyk.denys.windmillweather.utils.paging.ItemsLoadListener
import com.kalashnyk.denys.windmillweather.utils.paging.PagingAdapter

abstract class BasePagingFragment<V : ViewDataBinding>: BaseFragment<V>(),
    ItemsLoadListener<PagedList<BaseCardModel>> {

    protected var pagingAdapter: PagingAdapter = PagingAdapter(DiffCallbackBaseCardModel())

    protected var screenType : String = Pages.UNKNOWN.name

    abstract fun injectDependency(component: ViewModelComponent)

    abstract fun initListView()

    abstract fun getListView(): RecyclerView

    abstract fun getRefreshView(): SwipeRefreshLayout

    abstract fun getStateView() : MultiStateView

    abstract fun initObserver()

    abstract fun removeObserver()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayProgress()
        initListView()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    override fun onDestroyView() {
        removeObserver()
        super.onDestroyView()
    }

    private fun createDaggerDependencies() {
        activity?.apply { injectDependency((application as WindmillWeatherApp).getViewModelComponent()) }
    }
}