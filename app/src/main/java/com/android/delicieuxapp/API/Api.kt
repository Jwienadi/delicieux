package com.android.delicieuxapp.API

import com.android.testdelicieux.API.RestoDetailResponse
import retrofit2.Call
import retrofit2.http.GET

object Api {
    @JvmStatic
    inline fun <reified T> service() : T {
        return RetrofitClient.retrofitBuilder().create(T::class.java)
    }

}