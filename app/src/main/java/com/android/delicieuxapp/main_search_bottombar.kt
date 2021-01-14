package com.android.delicieuxapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextUtils.replace
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.android.delicieuxapp.model.Reqres
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.roughike.bottombar.BottomBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity2 : AppCompatActivity() {

    private var articles: MutableList<com.android.delicieuxapp.model.Restaurant> = mutableListOf()
    private lateinit var myAdapter: MyAdapter
    private lateinit var editTextSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        //get intent parameter
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_bottombar)

        val bottomBar = findViewById<View>(R.id.bottomBar1) as? BottomBar
        val firstFragment = FragmentHome()
        val secondFragment= FragmentProfile()

        setCurrentFragment(firstFragment)


        bottomBar?.setOnTabSelectListener { tabId ->

            if (tabId == R.id.tab_search) {
                setCurrentFragment(firstFragment)
            }
            if (tabId == R.id.tab_profile) {
                setCurrentFragment(secondFragment)
            }
        }

        // setup adapter
        myAdapter = MyAdapter(articles)

        // setup recycler_view
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view.adapter = myAdapter

        editTextSearch = findViewById(R.id.searchEt)

        // Search Button
        editTextSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString());
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }




        })

        //setup android networking
        AndroidNetworking.initialize(this)


        AndroidNetworking.get(
            "https://developers.zomato.com/api/v2.1/search?apikey=0d9669f4a2ef9bab2589dda088256b93")
            .build()
            .getAsObject(Reqres::class.java, object: ParsedRequestListener<Reqres> {
                override fun onResponse(response: Reqres) {
                    articles.addAll(response.restaurants)
                    myAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                }

            })

        bt_sign.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }









    }


    private fun filterList(filterItem: String) {

        var tempList: MutableList<com.android.delicieuxapp.model.Restaurant> = ArrayList()

        for (d in articles){

            if(filterItem in d.restaurant.name){
                tempList.add(d)

            }
        }
        myAdapter.updateList(tempList)
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment2,fragment)
            commit()
        }
}



