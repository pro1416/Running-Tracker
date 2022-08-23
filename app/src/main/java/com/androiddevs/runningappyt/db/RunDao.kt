package com.androiddevs.runningappyt.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.runningappyt.db.entities.Run

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM runs ORDER BY timestamp DESC")
    fun getAllRunsByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM runs ORDER BY avg_speed_in_km DESC")
    fun getAllRunsByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM runs ORDER BY distance_in_mtr DESC")
    fun getAllRunsByDistance(): LiveData<List<Run>>

    @Query("SELECT * FROM runs ORDER BY time_in_millis DESC")
    fun getAllRunsByTimeInMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM runs ORDER BY calories_burnt DESC")
    fun getAllRunsByCaloriesBurnt(): LiveData<List<Run>>

    @Query("SELECT SUM(time_in_millis) FROM runs")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT AVG(avg_speed_in_km) FROM runs")
    fun getTotalAvgSpeed(): LiveData<Float>

    @Query("SELECT SUM(calories_burnt) FROM runs")
    fun getTotalCaloriesBurnt(): LiveData<Int>

    @Query("SELECT SUM(distance_in_mtr) FROM runs")
    fun getTotalDistance(): LiveData<Int>

}