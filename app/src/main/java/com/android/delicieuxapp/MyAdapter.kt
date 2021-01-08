package com.android.delicieuxapp

import android.content.Context
import android.provider.ContactsContract
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.delicieuxapp.model.RestaurantX
import com.android.testdelicieux.API.RestoDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.txt_title
import kotlinx.android.synthetic.main.item_layout.view.txt_type
import kotlinx.android.synthetic.main.recycler_view_template.view.*

class MyAdapter (private val dataList: MutableList<RestaurantX>) : RecyclerView.Adapter<MyHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
      context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]

        val title = holder.itemView.txt_title
        val type = holder.itemView.txt_type
        val location = holder.itemView.txt_loc
        val img = holder.itemView.img_view
        val rating = holder.itemView.tx_rating

        val name = "${data.name}"
        title.text = name

        val tipe = "${data.cuisines}"
        type.text = tipe

        val lokasi = "${data.location}"
        location.text = lokasi

        val nilai = "${data.userRating}"
        rating.text = nilai

        Picasso.get()
            .load(data.photosUrl)
            .into(img)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show()

        }




    }

    override fun getItemCount(): Int = dataList.size
}

