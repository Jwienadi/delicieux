package com.android.delicieuxapp.model


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("text")
    val text: String
)