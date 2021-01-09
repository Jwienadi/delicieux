package com.android.delicieuxapp.model


import com.google.gson.annotations.SerializedName

data class Reqres(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>,
    @SerializedName("results_found")
    val resultsFound: Int,
    @SerializedName("results_shown")
    val resultsShown: Int,
    @SerializedName("results_start")
    val resultsStart: Int
)