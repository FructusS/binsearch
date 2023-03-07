package com.example.binsearch.data.models.request

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("number")
data class Number(

    val length: Int = 0,
    val luhn: Boolean = false
)