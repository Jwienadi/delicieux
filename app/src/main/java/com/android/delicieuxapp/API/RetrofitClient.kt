package com.android.delicieuxapp.API

import com.android.testdelicieux.API.Restaurant
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

    val instance : Restaurant by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Restaurant::class.java)
    }



    fun retrofitBuilder(): Retrofit {
        return retrofit
    }
}