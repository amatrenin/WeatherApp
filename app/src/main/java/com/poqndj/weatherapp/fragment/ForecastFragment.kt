package com.poqndj.weatherapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poqndj.weatherapp.ForecastAdapter
import com.poqndj.weatherapp.R
import com.poqndj.weatherapp.databinding.FragmentForecastBinding
import com.poqndj.weatherapp.model.forecast.ForecastResult

class ForecastFragment : Fragment() {

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
        val forecastResultList = listOf<ForecastResult>(
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
            ForecastResult("testMain", "testDescription", "testTemp", "testDate"),
        )
        adapter = ForecastAdapter(requireContext(), forecastResultList)
        binding?.rcForecast?.adapter = adapter
    }

    companion object {
        fun newInstance() = ForecastFragment()
    }
}