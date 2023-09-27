package com.poqndj.weatherapp.model.forecast

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Locations>,
    val message: Int
)