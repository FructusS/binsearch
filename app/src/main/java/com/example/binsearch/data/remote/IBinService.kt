package com.example.binsearch.data.remote

import com.example.binsearch.data.models.request.BIN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IBinService {
    @GET("{bin}")
    suspend fun getBin(@Path("bin") digits: String): Response<BIN>
}