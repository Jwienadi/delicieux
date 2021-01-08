package com.android.delicieuxapp.model


import com.google.gson.annotations.SerializedName

data class Resto(
    @SerializedName("restaurants")
    val restaurants: List<RestaurantX>,
    @SerializedName("results_found")
    val resultsFound: Int,
    @SerializedName("results_shown")
    val resultsShown: Int,
    @SerializedName("results_start")
    val resultsStart: Int
)