package com.android.delicieuxapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.android.delicieuxapp.model.Reqres
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    lateinit var myAdapter: MyAdapter
    val lm = LinearLayoutManager(activity)
    var articles: MutableList<com.android.delicieuxapp.model.Restaurant> = mutableListOf()
    lateinit var editTextSearch: EditText
    var sampleImages = intArrayOf(
        R.drawable.carosel1,
        R.drawable.carouselll22

    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(sampleImages[position])
        }
        carouselView.pageCount = sampleImages.size
        initView()


        AndroidNetworking.initialize(activity)


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

        editTextSearch = searchEt
        editTextSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString());
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }




        })

        //bt_sign.setOnClickListener {
           // val intent = Intent(activity, login::class.java)
           // startActivity(intent)
      //  }
    }



    fun initView(){
        myAdapter = MyAdapter(articles)
        recycler_view.layoutManager = lm
        recycler_view.addItemDecoration(DividerItemDecoration(activity, OrientationHelper.VERTICAL))
        recycler_view.adapter = myAdapter


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

}