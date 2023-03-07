package com.example.binsearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.binsearch.data.models.request.BIN
import kotlinx.coroutines.flow.Flow

@Dao
interface BINDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBIN(bin : BIN)

    @Query("SELECT * FROM bin order by id desc")
    fun getAllBIN(): Flow<List<BIN>>

}
