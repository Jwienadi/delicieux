package com.android.delicieuxapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.delicieuxapp.API.*
import com.android.delicieuxapp.API.MenuResto
import com.android.delicieuxapp.model.Reqres
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.iv_title
import kotlinx.android.synthetic.main.detail_resto.rb_title_star
import kotlinx.android.synthetic.main.detail_resto.tv_title_loc
import kotlinx.android.synthetic.main.detail_resto.tv_title_name
import kotlinx.android.synthetic.main.detail_resto.tv_title_rating
import kotlinx.android.synthetic.main.detail_resto.tv_title_type
import kotlinx.android.synthetic.main.menu_resto.*
import kotlinx.android.synthetic.main.review_resto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuResto : AppCompatActivity() {
    var b: Bundle? = null

    //    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        headerapicall()
        apicall()
        setContentView(R.layout.menu_resto)

//    linearLayoutManager = LinearLayoutManager(this)
//    recyclerView.layoutManager = linearLayoutManager
    }

    fun headerapicall() {
        b = intent.extras
     //   var id = b?.getInt("id")
        var id = 16507624
        if (id != null) {
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
                            var titleJenis = response.body()?.ResJenis?.get(0) + " - " + response.body()?.ResCuisines
                            tv_title_type.text = titleJenis

                            var rating = response.body()?.ResRating?.ResAngkaRating
                            rb_title_star.rating = rating?.toFloat()!!
                            if (rating == "0") {
                                rating = "No Rating"
                            }
                            tv_title_rating.text = rating
                            if (response.body()?.ResPhotoUrl == "") {
                                Picasso.get().load("https://krenova.bp3d.boyolali.go.id/images/no-image-available.jpg"
                                ).into(iv_title)
                            } else {
                                Picasso.get().load(response.body()?.ResPhotoUrl).fit().centerCrop().into(iv_title)
                            }
                        }

                        override fun onFailure(call: Call<RestoDetailResponse>, t: Throwable) {
                        }
                    })
        }

    }

    fun apicall() {
        b = intent.extras
      //  var id = b?.getInt("id")
        var id = 16507624
        if (id != null) {
            Api.service<Menu>()
                    .getMenuId(id)
                    .enqueue(object : Callback<DailyMenus> {
                        override fun onResponse(
                                call: Call<DailyMenus>,
                                response: Response<DailyMenus>
                        ) {
                            response.body()?.menuhead4?.map { daily_menu ->
                                daily_menu?.menuhead3?.menuhead2?.map { dishes ->
                                    val view: View = layoutInflater.inflate(R.layout.menu_fill, null)
                                    val tvNamamenu: TextView = view.findViewById(R.id.tv_namamenu1)
                                    tvNamamenu.setText(dishes.menuhead?.namamenu)
                                    val tvHargamenu: TextView = view.findViewById(R.id.tv_hargamenu1)
                                    tvHargamenu.setText(dishes.menuhead?.hargamenu)
                                    val tvnamamenu2 = dishes.menuhead?.namamenu

//                                    val tvReview: TextView = view.findViewById(R.id.tv_reviewtext)
//
//                                    val reviewtext: String
//                                    if (dishes.namamenu == "") {
//                                        reviewtext = dishes.namamenu.toString()
//                                    } else {
//                                        reviewtext = dishes.hargamenu.toString()
//                                    }
//                                    tvReview.setText(reviewtext)
                                   jj_menu.addView(view)
                              }
                            }
                        }

                        override fun onFailure(call: Call<DailyMenus>, t: Throwable) {
                        }

                    })

        }
    }
}

