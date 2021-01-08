package com.android.delicieuxapp


import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.android.delicieuxapp.API.Api
import com.android.delicieuxapp.API.RetrofitClient
import com.android.delicieuxapp.model.RestaurantX
import com.android.delicieuxapp.model.Resto
import com.android.testdelicieux.API.RestaurantInfoService
import com.android.testdelicieux.API.RestoDetailResponse
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.roughike.bottombar.BottomBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity() : AppCompatActivity() {
    private val dataList: MutableList<RestaurantX> = mutableListOf()
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup myadapter
        myAdapter = MyAdapter(dataList)

        // setup recycler_view
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view.adapter = myAdapter

        //setup android networking
        AndroidNetworking.initialize(this)

        AndroidNetworking.get("https://developers.zomato.com/documentation#!/restaurant/search")
            .addHeaders("token", "2b0724f8aec25e2946034f3e7dcb4920")
            .build()
            .getAsObject(Resto::class.java, object: ParsedRequestListener<Resto>{
                override fun onResponse(response: Resto?) {

                    if (response != null) {
                        dataList.addAll(response.restaurants)
                    }
                    myAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                }

            })
    }
}



/* val homeFragment = FragmentHome()
  val profileFragment = FragmentProfile()
 val bottomBar = findViewById<View>(R.id.bottomBar) as? BottomBar

 makeCurrentFragment(homeFragment)
 bottomBar?.setOnTabSelectListener { tabId ->

     if (tabId == R.id.tab_search) {
         makeCurrentFragment(homeFragment)
     }
     if (tabId == R.id.tab_profile) {
         makeCurrentFragment(profileFragment)
     }
 }*/



    /*private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.contentContainer, fragment)
            commit()
        }*/




