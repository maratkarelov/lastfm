package com.example.ritotest.network

import com.example.ritotest.data.models.response.Perfomer
import com.example.ritotest.data.models.response.WorkersWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerAPI {
    @GET("")
    fun readPerfomers(
        @Query("method") method: String,
        @Query("country") country: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String
    ): Call<WorkersWrapper>

    @GET("api/v1/details")
    fun readDetails(): Call<List<Perfomer>>
}
