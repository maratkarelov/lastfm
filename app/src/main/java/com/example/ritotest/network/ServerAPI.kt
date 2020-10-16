package com.example.ritotest.network

import com.example.ritotest.data.models.response.Perfomer
import com.example.ritotest.data.models.response.WorkersWrapper
import retrofit2.Call
import retrofit2.http.GET

interface ServerAPI {
    @GET("api/v1/interactions")
    fun readInteractions(): Call<WorkersWrapper>
    @GET("api/v1/details")
    fun readDetails(): Call<List<Perfomer>>
}
