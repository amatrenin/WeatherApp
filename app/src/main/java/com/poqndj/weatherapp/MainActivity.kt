package com.poqndj.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.poqndj.weatherapp.databinding.ActivityMainBinding
import com.poqndj.weatherapp.fragment.ForecastFragment
import com.poqndj.weatherapp.fragment.WeatherFragment
import com.poqndj.weatherapp.model.currentweather.CurrentWeather
import com.poqndj.weatherapp.network.RetrofitHelper
import com.poqndj.weatherapp.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    lateinit var tableLayout: TabLayout
    private val appId = "9b3c31e7dd3739416bf618e764e6d413"
    private val retrofitClient = RetrofitHelper.getInstance().create(WeatherApi::class.java)
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.viewPager2
        tableLayout = binding.tabLayout

        lifecycleScope.launch(Dispatchers.IO) {
//            val result = retrofitClient.getGeocoding("London", "1", appId)
//////            Log.d("test", "result ${result.body()} is success")
////            result.body()?.first()
//            result.isSuccessful
//
//            val latResult = result.body()?.first()?.lat ?: 0.0
//            val lonResult = result.body()?.first()?.lon ?: 0.0
////
//            val currentWeather =
//                retrofitClient.getCurrentWeather(latResult, lonResult, appId, units = "metric")
//            val forecast = retrofitClient.getForecast(latResult, lonResult, appId, units = "metric")
////

//            withContext(Dispatchers.Main) {
//                binding.locationLabel.text = "location $latResult, $lonResult"
//                binding.currentWeatherLabel.text =
//                    currentWeather.body()?.weather?.first()?.description
//                binding.forecastLabel.text =
//                    forecast.body()?.list?.first()?.weather?.first()?.description
//            }
        }
        prepareViewPager()

    }

    private fun prepareViewPager() {
        val fragmentList = arrayListOf(
            WeatherFragment.newInstance(),
            ForecastFragment.newInstance()
        )

        val tabTitleArray = arrayOf("Weather", "Forecast")

        viewPager.adapter = ViewPagerAdapter(this, fragmentList)

        TabLayoutMediator(tableLayout,viewPager) {tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}