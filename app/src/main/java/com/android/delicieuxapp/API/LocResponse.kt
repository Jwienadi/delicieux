package com.android.testdelicieux.API

import com.google.gson.annotations.SerializedName

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