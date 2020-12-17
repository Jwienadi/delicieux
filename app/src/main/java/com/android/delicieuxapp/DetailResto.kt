package com.android.delicieuxapp

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.delicieuxapp.API.Api
import com.android.testdelicieux.API.RestaurantInfoService
import com.android.testdelicieux.API.RestoDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailResto : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            apicall()
            setContentView(R.layout.detail_resto);

        }

        //    override fun onStart() {
//        super.onStart()
        fun apicall() {
            Api.service<RestaurantInfoService>()
                .getResInfo(1704205)
                .enqueue(object : Callback<RestoDetailResponse> {
                    override fun onResponse(
                        call: Call<RestoDetailResponse>,
                        response: Response<RestoDetailResponse>
                    ) {
                        var test=response.body()
                        tv_title_name.text = response.body()?.ResName
                        var location= response.body()?.ResLocData?.ResLocname + " , " + response.body()?.ResLocData?.ResCity
                        tv_title_loc.text= location
                        var titleJenis = response.body()?.ResJenis?.get(0) + " - " + response.body()?.ResCuisines
                        tv_title_type.text=titleJenis

                        var rating = response.body()?.ResRating?.ResAngkaRating
                        rb_title_star.rating= rating?.toFloat()!!
                        if (rating=="0"){
                            rating="No Rating"
                        }
                        tv_title_rating.text=rating
                        tv_content_currency.text=response.body()?.ResCurrency
                        tv_content_cost.text=response.body()?.ResPrice
                        //payment = details
                        tv_content_hour.text= response.body()?.ResTime?.replace(", ","\n")
                        tv_content_phone.text=response.body()?.ResPhone
                        tv_content_address.text=response.body()?.ResLocData?.ResAddress
                        Picasso.get().load(response.body()?.ResPhotoUrl).fit().centerCrop().into(iv_title)
                        val details = response.body()?.ResDetails
                        val payment: MutableList<String> = mutableListOf()

                        if("Cash" in details!!){
                            payment+="Cash"
                        }
                        if("Debit Card" in details){
                            payment+="Debit Card"
                        }
                        if("Debit Card" in details){
                            payment+="Credit Card"
                        }
                        tv_content_payment.text=payment.joinToString()

                        val timeofday: MutableList<String> = mutableListOf()
                        if("Breakfast" in details){
                            timeofday+="Breakfast"
                        }
                        if("Lunch" in details){
                            timeofday+="Lunch"
                        }
                        if("Dinner" in details){
                            timeofday+="Dinner"
                        }
                        tv_content_timeofday.text=timeofday.joinToString()

                        val infoDetail: MutableList<String> = mutableListOf()
                        if("Wifi" in details){
                            infoDetail+="Wifi"
                        }
                        if("Air Conditioned" in details){
                            infoDetail+="Air Conditioned"
                        }
                        if("Kid Friendly" in details){
                            infoDetail+="Kid Friendly"
                        }
                        if("Pet Friendly" in details){
                            infoDetail+="Pet Friendly"
                        }
                        if("Vegetarian Friendly" in details){
                            infoDetail+="Vegetarian Friendly"
                        }
                        if("Vegan Options" in details){
                            infoDetail+="Vegan Options"
                        }
                        if("Smoking Area" in details){
                            infoDetail+="Smoking Area"
                        }
                        if("Indoor Seating" in details && "Outdoor Seating" in details){
                            infoDetail+="Indoor & Outdoor Seating"
                        } else if("Indoor Seating" in details){
                            infoDetail+="Indoor Seating"
                        } else if("Outdoor Seating" in details){
                            infoDetail+="Outdoor Seating"
                        }
                        if("Table booking recommended" in details){
                            infoDetail+="Table Booking Recommended"
                        }
                        if("Table reservation required" in details){
                            infoDetail+="Table Reservation Required"
                        }

                        var relativelayout: RelativeLayout =findViewById(R.id.content_details_area)
                        infoDetail.forEach {
                            val view: View = layoutInflater.inflate(R.layout.detail_box_green, null)
                            val tvDetail: TextView = view.findViewById(R.id.tv_infogreen)
                            tvDetail.setText(it)
                            relativelayout.addView(view)
                        }

                        if("No Alcohol Available" in details){
                            val view: View = layoutInflater.inflate(R.layout.detail_box_red, null)
                            val tvDetail: TextView = view.findViewById(R.id.tv_infored)
                            tvDetail.setText("No Alcohol Available")
                            relativelayout.addView(view)
                        }
//Buat nanti yg highlight
//                    response.body()?.ResLocData?.mapIndexed { index, shipmentSummary ->
//                        tv_title_loc.text =
//                        tv1.text = index.toString()
//                    }
                    }

                    override fun onFailure(call: Call<RestoDetailResponse>, t: Throwable) {
                    }
                })
        }
    }
