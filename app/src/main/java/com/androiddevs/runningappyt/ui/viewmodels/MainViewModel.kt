package com.androiddevs.runningappyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.runningappyt.db.entities.Run
import com.androiddevs.runningappyt.other.SortType
import com.androiddevs.runningappyt.repositories.DatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val runsSortedByDate = databaseRepository.getAllRunsSortedByDate()

    private val runsSortedByDistance = databaseRepository.getAllRunsByDistance()

    private val runsSortedByAvgSpeed = databaseRepository.getAllRunsByAvgSpeed()

    private val runsSortedByCaloriesBurnt = databaseRepository.getAllRunsByCaloriesBurnt()

    private val runsSortedByTime = databaseRepository.getAllRunsByTimeInMillis()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate) {
            if (sortType == SortType.DATE) {
                it?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByDistance) {
            if (sortType == SortType.DISTANCE) {
                it?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByCaloriesBurnt) {
            if (sortType == SortType.CALORIES) {
                it?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByAvgSpeed) {
            if (sortType == SortType.AVG_SPEED) {
                it?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByTime) {
            if (sortType == SortType.TIME) {
                it?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) {
        when (sortType) {
            SortType.TIME -> runsSortedByTime.value?.let { runs.value = it }
            SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
            SortType.CALORIES -> runsSortedByCaloriesBurnt.value?.let { runs.value = it }
            SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
            SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        }

        this.sortType = sortType
    }


    fun insertRun(run: Run) {
        viewModelScope.launch {
            databaseRepository.insertRun(run)
        }
    }
}