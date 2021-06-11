package com.kalashnyk.denys.windmillweather.ui.activity.main

import android.view.MenuItem
import com.kalashnyk.denys.windmillweather.R
import com.kalashnyk.denys.windmillweather.base.ui.BaseActivity
import com.kalashnyk.denys.windmillweather.databinding.MainDataBinding
import com.kalashnyk.denys.windmillweather.di.component.ViewModelComponent
import com.kalashnyk.denys.windmillweather.ui.navigator.model.Pages
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.entity.CityEntity
import com.kalashnyk.denys.windmillweather.utils.extensions.hideKeyboard
import com.kalashnyk.denys.windmillweather.utils.settings.MAIN_TYPE_SCREEN

class MainActivity : BaseActivity<MainDataBinding>() {

    var currentCity: CityEntity? = null

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setupViewLogic(binder: MainDataBinding) {
        showRootChild()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun showRootChild() {
        openPage(handlePage())
        hideKeyboard()
    }

    private fun handlePage() = intent?.extras?.getSerializable(MAIN_TYPE_SCREEN) as Pages
}
