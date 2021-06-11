package com.kalashnyk.denys.windmillweather.utils.rx

import com.kalashnyk.denys.windmillweather.utils.settings.DEFAULT_RETRY_ATTEMPTS
import com.kalashnyk.denys.windmillweather.utils.settings.DEFAULT_TIMEOUT
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxTransformers {

    fun <T> singleTransformer(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
    }

    fun <T> observableTransformer(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .retry(DEFAULT_RETRY_ATTEMPTS)
    }

}