package com.androiddevs.runningappyt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.runningappyt.db.entities.Run

@Database(entities = [Run::class], version = 1)
@TypeConverters(Converter::class)
abstract class RunDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao

}