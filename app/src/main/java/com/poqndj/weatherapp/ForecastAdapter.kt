package com.poqndj.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.poqndj.weatherapp.databinding.FragmentForecastBinding
import com.poqndj.weatherapp.databinding.ItemForecastBinding
import com.poqndj.weatherapp.model.forecast.ForecastResult

class ForecastAdapter(
    private val fragmentContex: Context,
    private val forecastList: List<ForecastResult>
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemForecastBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(forecast: ForecastResult) {
            binding.itemRecyclerTemp.text = "Temp: ${forecast.temp}"
            binding.itemRecyclerDescription.text = "Temp: ${forecast.description}"
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