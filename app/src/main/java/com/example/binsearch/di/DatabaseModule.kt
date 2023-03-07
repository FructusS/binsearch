package com.example.binsearch.di

import android.content.Context
import androidx.room.Room
import com.example.binsearch.data.local.BINDao
import com.example.binsearch.data.local.BINDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDataBaseRoom(@ApplicationContext context: Context): BINDatabase =
        Room.databaseBuilder(context.applicationContext, BINDatabase::class.java, "bindatabase")
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideHistoryDao(dataBase: BINDatabase): BINDao = dataBase.binDao()
}