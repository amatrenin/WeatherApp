package com.poqndj.weatherapp.model.forecast

data class Forecast1(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastResult>,
    val message: Int
)