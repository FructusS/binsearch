package com.example.binsearch.data.models.request

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("bin")
data class BIN(
    @PrimaryKey (autoGenerate = true)
    val id : Long,
    @Embedded
    val bank: Bank,
    val brand: String?,
    @Embedded
    val country: Country,
    @Embedded
    val number: Number = Number(),
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)