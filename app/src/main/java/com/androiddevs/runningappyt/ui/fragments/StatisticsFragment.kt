package com.androiddevs.runningappyt.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.other.CustomMarker
import com.androiddevs.runningappyt.other.TrackingUtility
import com.androiddevs.runningappyt.ui.viewmodels.MainViewModel
import com.androiddevs.runningappyt.ui.viewmodels.StatisticViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.item_run.*
import java.lang.Math.round

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics) {

    private val statisticViewModel: StatisticViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setupChart()
    }

    private fun setupChart(){
        barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        barChart.axisLeft.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        barChart.axisRight.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        barChart.apply {
            description.text = "Avg Speed Over Time"
            legend.isEnabled = false
        }
    }

    private fun subscribeObservers(){
        statisticViewModel.totalTime.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalTime = TrackingUtility.getFormattedStopWatchTime(it)
                tvTotalTime.text = totalTime
            }
        })

        statisticViewModel.totalDistance.observe(viewLifecycleOwner, Observer {
            it?.let {
                val km = it/1000f
                val totalDistance = round(km*10f)/10f
                tvTotalDistance.text = "${totalDistance}km"
            }
        })

        statisticViewModel.totalAvgSpeed.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalSpeed = round(it*10f)/10f
                tvAverageSpeed.text = "${totalSpeed}km/h"
            }
        })

        statisticViewModel.totalCaloriesBurnt.observe(viewLifecycleOwner, Observer {
            it?.let {
                tvTotalCalories.text = "${it}kcal"
            }
        })

        statisticViewModel.runsSortedByDate.observe(viewLifecycleOwner, Observer {
            it?.let{
                val allAvgSpeeds = it.indices.map {
                        i -> BarEntry(i.toFloat(),it[i].avgSpeedInKm)
                }
                val barDataSet = BarDataSet(allAvgSpeeds,"Avg Speed over Time").apply {
                    valueTextColor = Color.WHITE
                    color = ContextCompat.getColor(requireContext(),R.color.colorAccent)
                }

                barChart.data = BarData(barDataSet)
                barChart.marker = CustomMarker(it.reversed(),requireContext(),R.layout.marker_view)
                barChart.invalidate()
            }
        })
    }
}