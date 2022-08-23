package com.androiddevs.runningappyt.other

import android.content.Context
import com.androiddevs.runningappyt.db.entities.Run
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.item_run.view.*
import kotlinx.android.synthetic.main.item_run.view.tvAvgSpeed
import kotlinx.android.synthetic.main.item_run.view.tvDate
import kotlinx.android.synthetic.main.item_run.view.tvDistance
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarker(
    val runs: List<Run>,
    context: Context,
    layoutId: Int
) : MarkerView(context, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width/2f,-height.toFloat())
    }
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }
        val runIndex = e.x.toInt()
        val run = runs.get(runIndex)

        val cal = Calendar.getInstance().apply {
            timeInMillis = run.timeInMillis
        }

        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        tvDateM.text = "Date: " + dateFormat.format(cal.time)

        val avgSpeedInKm = "${run.avgSpeedInKm}km/h"
        tvAvgSpeedM.text = avgSpeedInKm

        val distanceinMtr = "${run.distanceInMtr / 1000f} km"
        tvDistanceM.text = distanceinMtr

        tvDurationM.text = "Time: " + TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

        val calories = "${run.caloriesBurnt}kcal"
        tvCaloriesBurnedM.text = calories

    }
}