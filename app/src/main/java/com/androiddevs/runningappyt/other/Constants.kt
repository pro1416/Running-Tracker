package com.androiddevs.runningappyt.other

import android.graphics.Color

object Constants {
    const val RUN_DB_NAME = "run_db"
    const val REQUEST_CODE_LOCATION_PERMISSIONS = 1
    const val SERVICE_STARTED_OR_RESUMED = "SERVICE_STARTED_OR_RESUMED"
    const val SERVICE_PAUSED = "SERVICE_PAUSED"
    const val SERVICE_STOP = "SERVICE_STOP"
    const val ACTION_SHOW_TRACK_FRAGMENT = "SHOW_TRACKING_FRAG"
    const val NOTIFICATION_CHANNEL_ID = "run_track_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Run Tracking"
    const val NOTIFICATION_ID = 1
    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_UPDATE_INTERVAL = 2000L
    const val POLYLINE_COLOR = Color.RED
    const val POLYLINE_WIDTH = 8f
    const val MAP_ZOOM = 15f
    const val TIMER_UPDATE_DELAY = 50L
    const val SHARED_PREFS_NAME = "shared_pref_run"
    const val KEY_FIRST_TIME_TOGGLE = "KEY_FIRST_TIME_TOGGLE"
    const val KEY_NAME = "KEY_NAME"
    const val KEY_WEIGHT = "KEY_WEIGHT"
}