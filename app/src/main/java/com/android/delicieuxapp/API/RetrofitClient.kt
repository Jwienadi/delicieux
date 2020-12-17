package com.android.delicieuxapp.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://developers.zomato.com/api/v2.1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(NetworkInterceptor())
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun retrofitBuilder(): Retrofit {
        return retrofit
    }
}