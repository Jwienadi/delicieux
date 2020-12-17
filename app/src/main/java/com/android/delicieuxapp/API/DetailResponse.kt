package com.android.testdelicieux.API

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array

//buat ngakses value di api, dideclare variabelnya
//di open classnya kalo ada perlu inheritance
class RestoDetailResponse {
    @SerializedName("name")
    var ResName: String? = " a"
    @SerializedName("location")
//    //dis List<> is trouble all the time, try T
    var ResLocData: LocData? = null
    @SerializedName("cuisines")
    var ResCuisines: String? = ""
    @SerializedName("timings")
    var ResTime: String? = ""
    @SerializedName("average_cost_for_two")
    var ResPrice: String? = ""
    @SerializedName("currency")
    var ResCurrency: String? = ""
    @SerializedName("highlights")
    var ResDetails: List<String>? = null
    @SerializedName("featured_image")
    var ResPhotoUrl: String? = ""
    @SerializedName("phone_numbers")
    var ResPhone: String? = ""
    @SerializedName("establishment")
    var ResJenis: List<String>? = null
    @SerializedName("user_rating")
    var ResRating: UserRating? = null
}


//
class UserRating {
    @SerializedName("aggregate_rating")
    var ResAngkaRating: String? = ""
}

