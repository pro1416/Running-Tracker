package com.androiddevs.runningappyt.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.db.entities.Run
import com.androiddevs.runningappyt.other.TrackingUtility
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {
    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val differCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_run,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(run.image).into(ivRunImage)
            val cal = Calendar.getInstance().apply {
                timeInMillis = run.timeInMillis
            }

            val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            tvDate.text = "Date: "+dateFormat.format(cal.time)

            val avgSpeedInKm = "${run.avgSpeedInKm}km/h"
            tvAvgSpeed.text = avgSpeedInKm

            val distanceinMtr = "${run.distanceInMtr / 1000f} km"
            tvDistance.text = distanceinMtr

            tvTime.text = "Time: "+TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

            val calories = "${run.caloriesBurnt}kcal"
            tvCalories.text = calories
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}