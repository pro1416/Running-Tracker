package com.androiddevs.runningappyt.repositories

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androiddevs.runningappyt.db.RunDao
import com.androiddevs.runningappyt.db.entities.Run
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsByDate()

    fun getAllRunsByAvgSpeed() = runDao.getAllRunsByAvgSpeed()

    fun getAllRunsByDistance() = runDao.getAllRunsByDistance()

    fun getAllRunsByTimeInMillis() = runDao.getAllRunsByTimeInMillis()

    fun getAllRunsByCaloriesBurnt() = runDao.getAllRunsByCaloriesBurnt()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalCaloriesBurnt() = runDao.getTotalCaloriesBurnt()

    fun getTotalDistance() = runDao.getTotalDistance()
}