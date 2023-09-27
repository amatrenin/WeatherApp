package com.poqndj.weatherapp.model.forecast

import android.icu.text.CaseMap.Title

data class ForecastResult(
    val main: String,
    val description: String,
    val temp: String,
    val date: String
)
