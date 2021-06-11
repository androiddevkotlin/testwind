package com.kalashnyk.denys.windmillweather.di.module

import com.kalashnyk.denys.windmillweather.di.scope.ApiScope
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.ForecastRemoteDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.ForecastRemoteDataSourceImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.WeatherRemoteDataSource
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.WeatherRemoteDataSourceImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator.ServerCommunicator
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator.ServerCommunicatorImpl
import com.kalashnyk.denys.windmillweather.usecases.repository.remote_datasource.communicator.WeatherApiService
import com.kalashnyk.denys.windmillweather.utils.settings.WEATHER_API_URL
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

    @Provides
    @ApiScope
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(5, 30, TimeUnit.SECONDS))
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @ApiScope
    fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @ApiScope
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit = builder
        .baseUrl(WEATHER_API_URL).build()

    @Provides
    @ApiScope
    fun provideWeatherApi(retrofit: Retrofit): WeatherApiService = retrofit.create(WeatherApiService::class.java)

    @Provides
    @ApiScope
    fun provideCommunicator(apiService: WeatherApiService): ServerCommunicator =
        ServerCommunicatorImpl(apiService)

    @Provides
    @ApiScope
    fun provideForecastRemoteDataSource(communicator: ServerCommunicator): ForecastRemoteDataSource =
        ForecastRemoteDataSourceImpl(communicator)

    @Provides
    @ApiScope
    fun provideWeatherRemoteDataSource(communicator: ServerCommunicator): WeatherRemoteDataSource =
        WeatherRemoteDataSourceImpl(communicator)

}