package com.android.delicieuxapp

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.delicieuxapp.model.Reqres
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class MenuResto() : AppCompatActivity() {
        //private var articles: MutableList<com.android.delicieuxapp.model.Restaurant> = mutableListOf()
        //private lateinit var myAdapter: MyAdapter
        //private lateinit var editsearch: EditText

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.menu_resto)


            //setup android networking
            AndroidNetworking.initialize(this)

            AndroidNetworking.get("https://developers.zomato.com/api/v2.1/search?apikey=0d9669f4a2ef9bab2589dda088256b93")
                    .build()
                    .getAsObject(Reqres::class.java, object: ParsedRequestListener<Reqres> {
                        override fun onResponse(response: Reqres) {
                        }

                        override fun onError(anError: ANError?) {
                        }

                    })

        }

    }
