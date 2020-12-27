package com.android.delicieuxapp

import android.media.Rating
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.delicieuxapp.API.Api
import com.android.testdelicieux.API.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.detail_resto.iv_title
import kotlinx.android.synthetic.main.detail_resto.rb_title_star
import kotlinx.android.synthetic.main.detail_resto.tv_title_loc
import kotlinx.android.synthetic.main.detail_resto.tv_title_name
import kotlinx.android.synthetic.main.detail_resto.tv_title_rating
import kotlinx.android.synthetic.main.detail_resto.tv_title_type
import kotlinx.android.synthetic.main.review_resto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewResto : AppCompatActivity() {
//    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        headerapicall()
        apicall()
        setContentView(R.layout.review_resto)
//    linearLayoutManager = LinearLayoutManager(this)
//    recyclerView.layoutManager = linearLayoutManager
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
fun apicall() {
    Api.service<RestaurantReview>()
        .getResId(16774318)
        .enqueue(object : Callback<ReviewsResponse> {
            override fun onResponse(
                call: Call<ReviewsResponse>,
                response: Response<ReviewsResponse>
            ) {
            response.body()?.reviewhead?.map {review ->
                val view: View = layoutInflater.inflate(R.layout.review_fill, null)
                val tvNama: TextView = view.findViewById(R.id.tv_reviewname)
                tvNama.setText(review.reviewhead2?.ratinguser?.ratingname)
                val tvStarNum: TextView = view.findViewById(R.id.tv_reviewstarnum)
                tvStarNum.setText(review.reviewhead2?.ratingnumber)
                val rbStarNum: RatingBar= view.findViewById(R.id.rb_reviewstar)
                rbStarNum.setRating(review.reviewhead2?.ratingnumber?.toFloat()!!)
                val tvReview: TextView= view.findViewById(R.id.tv_reviewtext)


                val reviewtext:String
                if (review.reviewhead2?.reviewtitle == ""){
                    reviewtext=review.reviewhead2?.reviewfull.toString()
                } else if (review.reviewhead2?.reviewfull == ""){
                    reviewtext=review.reviewhead2?.reviewtitle.toString()
                } else {
                    reviewtext=review.reviewhead2?.reviewtitle + " - " + review.reviewhead2?.reviewfull
                }
                tvReview.setText(reviewtext)
                ll_reviews.addView(view)
            }
            }

            override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
            }
        })

}
}