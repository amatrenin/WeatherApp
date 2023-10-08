package com.poqndj.weatherapp

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TableLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputLayout
import com.poqndj.weatherapp.databinding.ActivityMainBinding
import com.poqndj.weatherapp.fragment.ForecastFragment
import com.poqndj.weatherapp.fragment.WeatherFragment
import com.poqndj.weatherapp.model.currentweather.CurrentWeather
import com.poqndj.weatherapp.network.RetrofitHelper
import com.poqndj.weatherapp.network.WeatherApi
import com.poqndj.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tableLayout: TabLayout
    private lateinit var inputField: TextInputLayout
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPager = binding.viewPager2
        tableLayout = binding.tabLayout
        inputField = binding.mainInputField

        inputField.setEndIconOnClickListener {

            val inputMetodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as
                    InputMethodManager
            if(inputMetodManager.isActive) {
                inputMetodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
            }

            lifecycleScope.launch(Dispatchers.Main) {
                mainViewModel.getCoordinates(inputField.editText?.text.toString())
            }
        }

        mainViewModel.coordinatesResult.observe(this) {
            lifecycleScope.launch(Dispatchers.Main) {
                mainViewModel.getCurrentWeather(it.lat, it.lon)
                mainViewModel.getForecast(it.lat, it.lon)
            }
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

        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}