package com.poqndj.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.poqndj.weatherapp.R
import com.poqndj.weatherapp.databinding.FragmentForecastBinding
import com.poqndj.weatherapp.databinding.FragmentWeatherBinding
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_CLEAR
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_CLOUDS
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_RAIN
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_SNOW
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl.Companion.WEATHER_TYPE_THUNDERSTORM
import com.poqndj.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    lateinit var weatherConstr: ConstraintLayout
    private val binding get() = _binding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.currentWeatherResult.observe(viewLifecycleOwner) {
            binding?.weatherTitleCloudy?.text = it.main
            binding?.weatherTitleCloudyDetails?.text = it.description
//            binding?.weatherContainerMain. = it.main.toString()

            binding?.weatherTitleDegrees?.text = it.temp.toString()
            binding?.weatherPressureValue?.text = it.pressure.toString()
            binding?.weatherHumidityValue?.text = it.humidity.toString()
            binding?.weatherWindSpeedValue?.text = it.windSpeed.toString()
            weatherConstr = binding?.weatherConstraint!!
            weatherConstr.setBackgroundColor(resources.getColor(android.R.color.transparent))

            when (it.main) {
                WEATHER_TYPE_CLEAR -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.clear
                        )
                    )
                }

                WEATHER_TYPE_RAIN -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.rain
                        )
                    )
                }

                WEATHER_TYPE_SNOW -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.snow
                        )
                    )
                }
//
                WEATHER_TYPE_THUNDERSTORM -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.thunderstorm
                        )
                    )
                }

                WEATHER_TYPE_CLOUDS -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.clouds
                        )
                    )
                }

                else -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.clouds
                        )
                    )
                }
            }
        }
    }

    companion object {
        fun newInstance() = WeatherFragment()

    }
}