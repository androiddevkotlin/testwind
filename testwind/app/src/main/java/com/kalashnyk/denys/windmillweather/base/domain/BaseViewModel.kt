package com.kalashnyk.denys.windmillweather.base.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.kalashnyk.denys.windmillweather.utils.logger.Logger
import com.kalashnyk.denys.windmillweather.utils.state.LoadingState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()
    val macroLoadingState = MediatorLiveData<LoadingState>()
    val error = MediatorLiveData<Throwable>()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun handleError(t: Throwable) {
        Logger.e(this::class, t)
        error.postValue(t)
    }
}