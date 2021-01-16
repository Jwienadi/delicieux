package com.android.delicieuxapp


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.android.delicieuxapp.API.Api
import com.android.delicieuxapp.model.RestaurantX
import com.android.delicieuxapp.API.RestoDetailResponse
import com.android.delicieuxapp.model.Reqres
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity() : AppCompatActivity(){
  //  private var articles: MutableList<com.android.delicieuxapp.model.Restaurant> = mutableListOf()
  //  private lateinit var myAdapter: MyAdapter
  //  private lateinit var editTextSearch: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar1.setOnNavigationItemSelectedListener(onBottomNavListener)

        var fr = supportFragmentManager.beginTransaction()
        fr.add(R.id.flFragment2, HomeFragment())
        fr.commit()

        // setup adapter
       // myAdapter = MyAdapter(articles)




        // setup recycler_view
       // recycler_view.layoutManager = LinearLayoutManager(this)
       // recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
       // recycler_view.adapter = myAdapter

       // editTextSearch = findViewById(R.id.searchEt)

        // Search Button
        /*editTextSearch.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString());
            }
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }




        })*/
       // var id = searchEt.query.toString()
        //setup android networking
      //  AndroidNetworking.initialize(this)


     //   AndroidNetworking.get(
       //     "https://developers.zomato.com/api/v2.1/search?apikey=0d9669f4a2ef9bab2589dda088256b93")
        //    .build()
        //    .getAsObject(Reqres::class.java, object: ParsedRequestListener<Reqres> {
         //       override fun onResponse(response: Reqres) {
        //            articles.addAll(response.restaurants)
         //           myAdapter.notifyDataSetChanged()
         //       }

         //       override fun onError(anError: ANError?) {
        //        }

        //    })

     //   bt_sign.setOnClickListener {
     //       val intent = Intent(this, login::class.java)
     //       startActivity(intent)
     //   }





    }

    private val onBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener { i ->
        var selectedFr: Fragment = HomeFragment()

        when(i.itemId){
            R.id.item_home -> {
                selectedFr = HomeFragment()
            }

            R.id.item_profile -> {
                selectedFr = AkunFragment()
            }
        }
        var fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.flFragment2, selectedFr)
        fr.commit()
        true
    }

   // private fun filterList(filterItem: String) {

   //     var tempList: MutableList<com.android.delicieuxapp.model.Restaurant> = ArrayList()

    //    for (d in articles){

   //         if(filterItem in d.restaurant.name){
   //             tempList.add(d)

   //         }
  //      }
  //      myAdapter.updateList(tempList)
  //  }


}






