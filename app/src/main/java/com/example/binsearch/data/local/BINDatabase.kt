package com.example.binsearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binsearch.data.models.request.BIN

@Database(entities = [BIN::class], version = 1)
abstract class BINDatabase : RoomDatabase() {
    abstract  fun binDao() : BINDao
}