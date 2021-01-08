package com.android.delicieuxapp.model


import com.google.gson.annotations.SerializedName

data class AllReviews(
    @SerializedName("reviews")
    val reviews: List<Review>
)