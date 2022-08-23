package com.androiddevs.runningappyt.db.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runs")
data class Run(
    @ColumnInfo(name = "image")
    var image: Bitmap? = null,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0L,

    @ColumnInfo(name = "avg_speed_in_km")
    var avgSpeedInKm: Float = 0F,

    @ColumnInfo(name = "distance_in_mtr")
    var distanceInMtr: Int = 0,

    @ColumnInfo(name = "time_in_millis")
    var timeInMillis: Long = 0L,

    @ColumnInfo(name = "calories_burnt")
    var caloriesBurnt: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}