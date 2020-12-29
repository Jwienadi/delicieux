package com.android.delicieuxapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.delicieuxapp.API.Api
import com.android.testdelicieux.API.RestaurantInfoService
import com.android.testdelicieux.API.RestoDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter : MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun init() {
        recyclerView = findViewById(R.id.recycler_view)
        var data = ArrayList<Restaurant>()
        Api.service<RestaurantInfoService>()
            .getResInfo(1704205)
            .enqueue(object : Callback<RestoDetailResponse> {
                override fun onResponse(
                    call: Call<RestoDetailResponse>,
                    response: Response<RestoDetailResponse>
                ) {
                    var title = response.body()?.ResName
                    var location= response.body()?.ResLocData?.ResLocname + " , " + response.body()?.ResLocData?.ResCity
                    var titleJenis = response.body()?.ResJenis?.get(0) + " - " + response.body()?.ResCuisines
                    var rating = response.body()?.ResRating?.ResAngkaRating
                    if (rating=="0"){
                        rating="No Rating"
                    }
                    //var image = Picasso.get().load(response.body()?.ResPhotoUrl).fit().centerCrop().into(img_view)
                      //  data.add(Restaurant(image,title.toString(),titleJenis,location, rating.toFloat()))
                    if (rating != null) {
                        data.add(Restaurant(R.drawable.ex,title.toString(),titleJenis,location, rating.toFloat()))
                    }

                }

                override fun onFailure(call: Call<RestoDetailResponse>, t: Throwable) {
                }
            })
        //data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya",3.5))
        //data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya", 4.5))
        //data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya",3.5))
        //data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya", 4.5))
        adapter = MyAdapter(data)
    }



}
