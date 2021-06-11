package com.kalashnyk.denys.windmillweather.ui.activity.splash

import android.os.Handler
import com.kalashnyk.denys.windmillweather.R

import com.kalashnyk.denys.windmillweather.base.ui.BaseActivity
import com.kalashnyk.denys.windmillweather.databinding.SplashBinding
import com.kalashnyk.denys.windmillweather.di.component.ViewModelComponent
import com.kalashnyk.denys.windmillweather.ui.navigator.model.Pages
import com.kalashnyk.denys.windmillweather.utils.settings.DELAY_3000

class SplashActivity : BaseActivity<SplashBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun setupViewLogic(binder: SplashBinding) {
        Handler().postDelayed({
            navigator.openMainScreen(Pages.CITIES)
            finish()
        }, DELAY_3000)
    }
}
