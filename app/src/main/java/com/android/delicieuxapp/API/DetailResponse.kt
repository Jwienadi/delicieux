package com.android.testdelicieux.API

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Array

//buat ngakses value di api, dideclare variabelnya
//di open classnya kalo ada perlu inheritance
class RestoDetailResponse {
    @SerializedName("name")
    var ResName: String? = "a"
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

class LocData {
    @SerializedName("address")
    var ResAddress: String? = ""
    @SerializedName("latitude")
    var ResLat: String? = ""
    @SerializedName("longitude")
    var ResLong: String? = ""
    @SerializedName("locality")
    var ResLocname: String? = ""
    @SerializedName("city")
    var ResCity: String? = ""
}
//
class UserRating {
    @SerializedName("aggregate_rating")
    var ResAngkaRating: String? = ""
}
//---------------------------------------------------------------------------
class ReviewsResponse {
    @SerializedName("user_reviews")
    var reviewhead: List<Review>? = null
}
class Review {
    @SerializedName("review")
    var reviewhead2: Reviewed? = null
}
class Reviewed{
    @SerializedName("rating")
    var ratingnumber: String? = ""
    @SerializedName("timestamp")
    var ratingtime: String? = ""
    @SerializedName("rating_text")
    var reviewtitle: String? = ""
    @SerializedName("review_text")
    var reviewfull: String? = ""
    @SerializedName("user")
    var ratinguser: user? = null
}
class user{
    @SerializedName("name")
    var ratingname: String? = ""
}