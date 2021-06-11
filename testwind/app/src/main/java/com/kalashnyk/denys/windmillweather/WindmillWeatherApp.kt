package com.kalashnyk.denys.windmillweather

import android.app.Application
import androidx.room.Room
import com.kalashnyk.denys.windmillweather.di.component.*
import com.kalashnyk.denys.windmillweather.di.module.*
import com.kalashnyk.denys.windmillweather.usecases.repository.datasource.database.AppDatabase
import com.kalashnyk.denys.windmillweather.utils.settings.APP_DATABASE

class WindmillWeatherApp : Application() {

    private lateinit var viewModelComponent: ViewModelComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun getViewModelComponent(): ViewModelComponent = viewModelComponent

    private fun initDagger() {
        val apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()

        val dbComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(getAppDatabase()))
            .build()

        val repoComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(dbComponent)
            .repositoryModule(RepositoryModule())
            .build()

        val interactorComponent = DaggerInteractorComponent.builder()
            .repositoryComponent(repoComponent)
            .interactorModule(InteractorModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .interactorComponent(interactorComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    private fun getAppDatabase() = Room.databaseBuilder(this, AppDatabase::class.java, APP_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}