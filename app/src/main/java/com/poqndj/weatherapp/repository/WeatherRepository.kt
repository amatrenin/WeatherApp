package com.poqndj.weatherapp.repository

import com.poqndj.weatherapp.model.currentweather.Coord
import com.poqndj.weatherapp.model.currentweather.Weather
import com.poqndj.weatherapp.model.currentweather.WeatherResult
import com.poqndj.weatherapp.model.forecast.FiveDayForecast

/**
 * Provides API connection with https://opanweathermap.org/
 */
interface WeatherRepository {

    /**
     * Getting location info like lat and lon
     */
    suspend fun getLocationCoordinates(city: String): Coord

    /**
     * Getting current weather for specific place by provide lat and lon
     */
    suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResult

    /**
     * Getting forecast for specific place by provide lat and lon
     */
    suspend fun getForecast(lat: Double, lon: Double): FiveDayForecast
}