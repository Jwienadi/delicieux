package com.android.delicieuxapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.delicieuxapp.API.Api
import com.android.delicieuxapp.API.RestaurantInfoService
import com.android.delicieuxapp.API.RestoDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.iv_title
import kotlinx.android.synthetic.main.detail_resto.rb_title_star
import kotlinx.android.synthetic.main.detail_resto.tv_title_loc
import kotlinx.android.synthetic.main.detail_resto.tv_title_name
import kotlinx.android.synthetic.main.detail_resto.tv_title_rating
import kotlinx.android.synthetic.main.detail_resto.tv_title_type
import kotlinx.android.synthetic.main.menu_resto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMenuResto : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.menu_resto, container, false)

        val args = arguments
        val id = args!!.getInt("ID", 0)
        headerapicall(id)


        return view
    }

    fun headerapicall(id: Int) {
        Api.service<RestaurantInfoService>()
                .getResInfo(id)
                .enqueue(object : Callback<RestoDetailResponse> {
                    override fun onResponse(
                            call: Call<RestoDetailResponse>,
                            response: Response<RestoDetailResponse>
                    ) {

                        tv_title_name.text = response.body()?.ResName
                        var location = response.body()?.ResLocData?.ResLocname + " , " + response.body()?.ResLocData?.ResCity
                        tv_title_loc.text = location
                        var titleJenis: String
                        if (response.body()?.ResJenis?.isEmpty()!!) {
                            titleJenis = response.body()?.ResCuisines!!
                        } else {
                            titleJenis = response.body()?.ResJenis?.get(0) + " - " + response.body()?.ResCuisines
                        }
                        tv_title_type.text = titleJenis

                        var rating = response.body()?.ResRating?.ResAngkaRating
                        rb_title_star.rating = rating?.toFloat()!!
                        if (rating == "0") {
                            rating = "No Rating"
                        }
                        tv_title_rating.text = rating
                        val restoimg = response.body()?.ResPhotoUrl
                        if (restoimg == "") {
                            Picasso.get().load("https://krenova.bp3d.boyolali.go.id/images/no-image-available.jpg").fit().centerCrop().into(iv_title)
                        } else {
                            Picasso.get().load(restoimg).fit().centerCrop().into(iv_title)
                        }
                    }

                    override fun onFailure(call: Call<RestoDetailResponse>, t: Throwable) {
                    }
                })

    }
}