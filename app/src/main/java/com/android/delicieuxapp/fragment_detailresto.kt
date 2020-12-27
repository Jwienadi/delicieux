package com.android.delicieuxapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.delicieuxapp.API.Api
import com.android.testdelicieux.API.RestaurantInfoService
import com.android.testdelicieux.API.RestoDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.detail_resto.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class FragmentDetailResto : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.detail_resto, container, false)

        headerapicall()
        apicall(view)

        view.btn_gmaps.setOnClickListener {
            val url = it.getTag()
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url as String?)
            startActivity(i)
        }
        view.btn_call.setOnClickListener{
            try {
                val notelp = it.getTag().toString().trim()
                val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(notelp)))
                startActivity(i)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
        return view
    }

    fun headerapicall(){
        Api.service<RestaurantInfoService>()
            .getResInfo(1704205)
            .enqueue(object : Callback<RestoDetailResponse> {
                override fun onResponse(
                    call: Call<RestoDetailResponse>,
                    response: Response<RestoDetailResponse>
                ) {

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
                    Picasso.get().load(response.body()?.ResPhotoUrl).fit().centerCrop().into(iv_title)
                }

                override fun onFailure(call: Call<RestoDetailResponse>, t: Throwable) {
                }
            })

    }

    fun apicall(view: View) {
        Api.service<RestaurantInfoService>()
            .getResInfo(1704205)
            .enqueue(object : Callback<RestoDetailResponse> {
                override fun onResponse(
                    call: Call<RestoDetailResponse>,
                    response: Response<RestoDetailResponse>
                ) {

                    tv_content_currency.text=response.body()?.ResCurrency
                    tv_content_cost.text=response.body()?.ResPrice
                    //payment = details
                    tv_content_hour.text= response.body()?.ResTime?.replace(", ","\n")
                    val notelp=response.body()?.ResPhone
                    tv_content_phone.text=notelp
                    btn_call.tag=notelp
                    tv_content_address.text=response.body()?.ResLocData?.ResAddress
                    val loc_lat=response.body()?.ResLocData?.ResLat
                    val loc_long=response.body()?.ResLocData?.ResLong
                    val maps_url= "https://www.google.com/maps/dir/?api=1&destination=$loc_lat,$loc_long"
                    btn_gmaps.tag=maps_url
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

                    var flowLayout: com.nex3z.flowlayout.FlowLayout =view.findViewById(R.id.content_details_area)
                    infoDetail.forEach {
                        val view: View = layoutInflater.inflate(R.layout.detail_box_green, null)
                        val tvDetail: TextView = view.findViewById(R.id.tv_infogreen)
                        tvDetail.setText(it)
                        flowLayout.addView(view)
                    }

                    if("No Alcohol Available" in details){
                        val view: View = layoutInflater.inflate(R.layout.detail_box_red, null)
                        val tvDetail: TextView = view.findViewById(R.id.tv_infored)
                        tvDetail.setText("No Alcohol Available")
                        flowLayout.addView(view)
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