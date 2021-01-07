package com.android.delicieuxapp

data class MainModel (val result: ArrayList<Result>){
    data class Result (val id: Int, val title: String, val type: String, val location: String,
                       val rating: Double, val image: String )

}