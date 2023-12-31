package com.poqndj.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poqndj.weatherapp.ForecastAdapter
import com.poqndj.weatherapp.R
import com.poqndj.weatherapp.databinding.FragmentForecastBinding
import com.poqndj.weatherapp.model.forecast.ForecastResult
import com.poqndj.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding
    private lateinit var rcView: RecyclerView
    private lateinit var adapter: ForecastAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding?.rcForecast?.layoutManager = layoutManager

        mainViewModel.forecastResult.observe(viewLifecycleOwner) {
            val foreastResultList = mutableListOf<ForecastResult>()
            it.list.forEach {
                foreastResultList.add(
                    ForecastResult(
                        main = it.weather.first().main,
                        description = it.weather.first().description,
                        temp = it.main.temp.toString(),
                        date = it.dt_txt
                    )
                )
            }

            adapter = ForecastAdapter(requireContext(), foreastResultList)
            binding?.rcForecast?.adapter = adapter
        }
    }

    companion object {
        fun newInstance() = ForecastFragment()
    }
}