package com.android.delicieuxapp.model


import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("restaurant")
    val restaurant: RestaurantX
)