package com.android.delicieuxapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya", 4.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya",3.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya", 4.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya",3.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya", 4.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya",3.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya", 4.5))
        data.add(Restaurant(R.drawable.ex,"Italiano","Fine Dining","Surabaya",3.5))
        adapter = MyAdapter(data)
    }

}
