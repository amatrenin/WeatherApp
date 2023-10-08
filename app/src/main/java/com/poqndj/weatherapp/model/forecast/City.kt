package com.poqndj.weatherapp.model.forecast

import com.poqndj.weatherapp.model.currentweather.Coord

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)