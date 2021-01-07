package com.android.delicieuxapp


import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.delicieuxapp.API.Api
import com.android.delicieuxapp.API.RetrofitClient
import com.android.testdelicieux.API.Restaurant
import com.android.testdelicieux.API.RestaurantInfoService
import com.android.testdelicieux.API.RestoDetailResponse
import com.roughike.bottombar.BottomBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity() : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    lateinit var resAdapter: ResAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDatafromApi()
    }

    private fun setupRecyclerView(){
        resAdapter = ResAdapter(arrayListOf())
        recycler_view.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = resAdapter
        }
    }

    private fun getDatafromApi(){
        Api.service<Restaurant>()
            .getRes()
            .enqueue(object : Callback<MainModel> {
                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    printLog("onFailure: $t")
                }

                override fun onResponse(call: Call<MainModel>, response: Response<MainModel>) {
                    if(response.isSuccessful){
                        showData(response.body()!!)
                    }
                }
            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(data:MainModel){
        val results = data.result
        for (result in results) {
            printLog("${result.title}")
        }
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




