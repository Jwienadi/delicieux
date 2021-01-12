package com.android.delicieuxapp


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.android.delicieuxapp.API.Api
import com.android.delicieuxapp.model.RestaurantX
import com.android.delicieuxapp.API.Restaurant
import com.android.delicieuxapp.API.RestoDetailResponse
import com.android.delicieuxapp.model.Reqres
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity() : AppCompatActivity(){
    private var articles: MutableList<com.android.delicieuxapp.model.Restaurant> = mutableListOf()
    private lateinit var myAdapter: MyAdapter
    private lateinit var editsearch :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup adapter
        myAdapter = MyAdapter(articles)

        editsearch = findViewById(R.id.sb_search)
        //query search
            editsearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                filterList(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

            }


        })
        // setup recycler_view
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view.adapter = myAdapter

        //setup android networking
        AndroidNetworking.initialize(this)

        AndroidNetworking.get("https://developers.zomato.com/api/v2.1/search?apikey=0d9669f4a2ef9bab2589dda088256b93")
            .build()
            .getAsObject(Reqres::class.java, object: ParsedRequestListener<Reqres> {
                override fun onResponse(response: Reqres) {
                    articles.addAll(response.restaurants)
                    myAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                }

            })



    }

    private fun filterList(filterItem: String) {
        var tempList:MutableList<com.android.delicieuxapp.model.Restaurant> = ArrayList()

        for (d in articles){
            if(filterItem in d.restaurant.name.toString()){

                tempList.add(d)
            }
        }
        myAdapter.updateList(tempList)
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




