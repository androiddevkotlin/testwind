package com.kalashnyk.denys.windmillweather.base.ui

import android.app.ActionBar
import android.os.Bundle
import androidx.annotation.DrawableRes

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kalashnyk.denys.windmillweather.base.domain.BaseViewModel
import com.kalashnyk.denys.windmillweather.utils.extensions.hideKeyboard
import com.kalashnyk.denys.windmillweather.utils.extensions.showSnack
import com.kalashnyk.denys.windmillweather.utils.extensions.showToast
import com.kalashnyk.denys.windmillweather.utils.state.LoadingState

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected lateinit var viewBinder: V

    private val appBar: ActionBar? = activity?.actionBar

    abstract fun getLayoutId(): Int

    abstract fun setupViewLogic(binder : V)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> fragmentManager?.popBackStackImmediate()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        setupViewLogic(this.viewBinder)
        return viewBinder.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()?.macroLoadingState?.observe(viewLifecycleOwner,
            Observer { it?.let { onLoadingStateChanged(it) } })
    }

    open fun reset() {}

    protected open fun getParentActivity(): BaseActivity<*> = requireActivity() as BaseActivity<*>

    protected fun setupAppBar(title: Int, back: Boolean = false) {
        getParentActivity().setupAppBar(title, back)
    }

    protected fun setupAppBar(title: String, back: Boolean = false) {
        getParentActivity().setupAppBar(title, back)
    }

    protected open fun getViewModel() : BaseViewModel? {
        return null
    }

    protected open fun onLoadingStateChanged(loadingState : LoadingState) {}

    protected fun showToast(text: String) = activity?.showToast(text)

    protected fun showSnack(text: String) = activity?.showSnack(text)

    protected fun hideKeyboard() = activity?.hideKeyboard()
}