package com.android.testdelicieux.API

import com.android.delicieuxapp.MainModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//BELUM DIUBAH SAMSEK, BUAT GET DATA(valu yg di pass di url nanti)
interface RestaurantInfoService {
    @GET("restaurant")
        fun getResInfo(
        @Query("res_id") res_id: Int
    ): Call<RestoDetailResponse>
}
interface Restaurant {
    @GET("restaurant")
    fun getRes(): Call<MainModel>
}


interface RestaurantReview {
    @GET("reviews")
    fun getResId(
        @Query("res_id") res_id: Int
    ): Call<ReviewsResponse>
}