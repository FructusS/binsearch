package com.example.binsearch.data.repository

import com.example.binsearch.data.local.BINDao
import com.example.binsearch.data.models.request.BIN
import com.example.binsearch.data.remote.IBinService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import retrofit2.Response

import javax.inject.Inject

class BINRepository @Inject constructor(private val iBinService: IBinService, private val binDao: BINDao) {


    suspend fun getBIN(digits : String) : Response<BIN> {
        return iBinService.getBin(digits)
    }

    fun getBinList(): Flow<List<BIN>> {
        return binDao.getAllBIN()
    }

    fun addBIN(bin: BIN) = runBlocking{
        try {
            binDao.addBIN(bin)
        }
        catch (_: Exception){
        }
    }

}