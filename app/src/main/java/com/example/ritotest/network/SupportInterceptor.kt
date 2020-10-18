package com.example.ritotest.network

import okhttp3.*
import java.io.IOException

class SupportInterceptor() : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val interceptor = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
        try {
            return chain.proceed(interceptor.build())
        } catch (e: java.lang.Exception) {
            throw e
        }
    }
}