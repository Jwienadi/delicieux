package com.android.delicieuxapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.delicieuxapp.model.Restaurant
import com.android.delicieuxapp.model.RestaurantX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class MyAdapter (private val articles:MutableList<Restaurant>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = articles[position]

        val title = holder.itemView.txt_title
        val type = holder.itemView.txt_type
        val location = holder.itemView.txt_loc
        val rating = holder.itemView.tv_rating
       val rating2 = holder.itemView.tx_rating
        val img = holder.itemView.img_view


        val name = data.restaurant.name
        title.text = name

        val tape = data.restaurant.cuisines
        type.text = tape

       val loki = data.restaurant.location.address
       location.text = loki

        val nalani = data.restaurant.userRating.aggregateRating
         rating.rating = nalani.toFloat()

        val rabat = data.restaurant.userRating.aggregateRating
        rating2.text = rabat

        Picasso.get().load(data.restaurant.photosUrl).into(img)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show()

        }


    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

