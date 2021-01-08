package com.android.delicieuxapp.model


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("review")
    val review: List<Any>
)