package com.poqndj.weatherapp.model.forecast

data class FiveDayForecast(
    val city: City,
    val cnt: Int,
    val list: List<Forecast>,
    val cod: String,
    val message: Int
)
