package com.android.delicieuxapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.android.delicieuxapp.API.*
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

class FragmentMenuResto : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.menu_resto, container, false)


        val args = arguments
        val id = args!!.getInt("ID", 0)
        headerapicall(id)
        apicall(view, id)


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

    fun apicall(view: View, id: Int) {
        Api.service<Menu>()
                .getMenuId(id)
                .enqueue(object : Callback<DailyMenus> {
                    override fun onResponse(
                            call: Call<DailyMenus>,
                            response: Response<DailyMenus>
                    ) {

                        if (response.body()?.menuhead4 == null) {
                            tv_nomenu.visibility=View.VISIBLE
                        } else {
                            tv_nomenu.visibility=View.GONE
                            response.body()?.menuhead4?.map { daily_menu ->
                                daily_menu?.menuhead3?.menuhead2?.map { dishes ->
                                    val view: View = layoutInflater.inflate(R.layout.menu_fill, null)
                                    val tvNamamenu: TextView = view.findViewById(R.id.tv_namamenu1)
                                    tvNamamenu.setText(dishes.menuhead?.namamenu)
                                    val tvHargamenu: TextView =
                                        view.findViewById(R.id.tv_hargamenu1)
                                    tvHargamenu.setText(dishes.menuhead?.hargamenu)

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
                    }
                    override fun onFailure(call: Call<DailyMenus>, t: Throwable) {
                    }

                })

    }
}
