package com.android.delicieuxapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.delicieuxapp.API.Api
import com.android.testdelicieux.API.RestaurantInfoService
import com.android.testdelicieux.API.RestoDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_resto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var titleList = mutableListOf<String>()
    private var typeList = mutableListOf<String>()
    private var locList = mutableListOf<String>()
    private var ratList = mutableListOf<Double>()
    private var imageList = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postToList()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RecyclerAdapter(titleList,typeList,locList,ratList,imageList)

    }

    private fun addToList(title: String, type: String, location: String, rating: Double, image: Int){
        titleList.add(title)
        typeList.add(type)
        locList.add(location)
        ratList.add(rating)
        imageList.add(image)
    }

    private fun postToList(){
        for (i in 1..25){
            addToList("Italiaon","Fine Dining","Surabaya",3.5,R.drawable.ex)
        }


    }

}

