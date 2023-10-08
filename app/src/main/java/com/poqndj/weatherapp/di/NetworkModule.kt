package com.poqndj.weatherapp.di

import com.poqndj.weatherapp.network.RetrofitHelper
import com.poqndj.weatherapp.network.WeatherApi
import com.poqndj.weatherapp.repository.WeatherRepository
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    fun provideRequestApi(): WeatherApi {
        return RetrofitHelper.getInstance().create(WeatherApi::class.java)
    }
}