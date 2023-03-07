package com.example.binsearch.data.models.request

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("bank")
data class Bank(

    val city: String = "",
    @ColumnInfo("bank_name")
    val name: String = "",
    val phone: String = "",
    val url: String = ""
)