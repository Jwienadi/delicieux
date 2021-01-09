package com.android.delicieuxapp.API

import com.android.delicieuxapp.API.RestoDetailResponse
import com.android.delicieuxapp.API.ReviewsResponse
import com.android.delicieuxapp.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//BELUM DIUBAH SAMSEK, BUAT GET DATA(valu yg di pass di url nanti)
interface RestaurantInfoService {
    @GET("restaurant")
        fun getResInfo(
        @Query("res_id") res_id: Int
    ): Call<RestoDetailResponse>
}

interface Restaurant {
    @GET("search")
    fun getSearch(
        @Query("q") q : String
    ): Call<MutableList<Restaurant>>
}

interface RestaurantReview {
    @GET("reviews")
    fun getResId(
        @Query("res_id") res_id: Int
    ): Call<ReviewsResponse>
}