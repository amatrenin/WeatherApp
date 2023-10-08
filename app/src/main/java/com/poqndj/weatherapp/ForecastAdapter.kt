package com.poqndj.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.poqndj.weatherapp.databinding.FragmentForecastBinding
import com.poqndj.weatherapp.databinding.ItemForecastBinding
import com.poqndj.weatherapp.model.forecast.ForecastResult
import com.poqndj.weatherapp.repository.WeatherRepositoryImpl
import java.text.SimpleDateFormat
import java.util.Locale

class ForecastAdapter(
    private val fragmentContex: Context,
    private val forecastList: List<ForecastResult>
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemForecastBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(forecast: ForecastResult) {
            val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputDateFormat = SimpleDateFormat("d MMMM HH:mm", Locale.getDefault())

            val date = inputDateFormat.parse(forecast.date)
            val outputDate = date?.let { outputDateFormat.format(it) }

            binding.itemRecyclerDate.text = "$outputDate"
            binding.itemRecyclerTemp.text = "${forecast.temp} C"
            binding.itemRecyclerDescription.text = "${forecast.description}"
            when(forecast.main) {
                WeatherRepositoryImpl.WEATHER_TYPE_CLEAR -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.clear
                        )
                    )
                }
                WeatherRepositoryImpl.WEATHER_TYPE_CLOUDS -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.clouds
                        )
                    )
                }
                WeatherRepositoryImpl.WEATHER_TYPE_RAIN -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.rain
                        )
                    )
                }
                WeatherRepositoryImpl.WEATHER_TYPE_THUNDERSTORM -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.thunderstorm
                        )
                    )
                }
                WeatherRepositoryImpl.WEATHER_TYPE_SNOW -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.snow
                        )
                    )
                }
                else -> {
                    binding?.weatherImage?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.snow))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, fragmentContex)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = forecastList[position]
        holder.bindItem(forecast)
    }
}