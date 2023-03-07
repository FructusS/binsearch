package com.example.binsearch.data.models.request

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("country")
data class Country(

    val alpha2: String = "",
    val currency: String = "",
    val emoji: String = "",
    val latitude: Float = 0f,
    val longitude: Float = 0f,
    @ColumnInfo("country_name")
    val name: String = "",
    val numeric: String= ""
)