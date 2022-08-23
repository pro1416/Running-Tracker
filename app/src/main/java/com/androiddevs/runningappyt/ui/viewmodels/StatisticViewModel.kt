package com.androiddevs.runningappyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.androiddevs.runningappyt.repositories.DatabaseRepository

class StatisticViewModel @ViewModelInject constructor(
    val databaseRepository: DatabaseRepository
) : ViewModel() {

    val totalTime = databaseRepository.getTotalTimeInMillis()

    val totalDistance = databaseRepository.getTotalDistance()

    val totalCaloriesBurnt = databaseRepository.getTotalCaloriesBurnt()

    val totalAvgSpeed = databaseRepository.getTotalAvgSpeed()

    val runsSortedByDate = databaseRepository.getAllRunsSortedByDate()
}