package com.android.delicieuxapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.testdelicieux.API.RestoDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_resto.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.txt_title
import kotlinx.android.synthetic.main.item_layout.view.txt_type
import kotlinx.android.synthetic.main.recycler_view_template.view.*

class ResAdapter (private val list: ArrayList<RestoDetailResponse>)
    : RecyclerView.Adapter<ResAdapter.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.txt_title.text = list[position].ResName
        holder.view.txt_type.text = list[position].ResCuisines
        holder.view.txt_loc.text = list[position].ResLocData.toString()
    }

    override fun getItemCount() = list.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {}(
    LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
    )


}

    /*fun setData(data : List<MainModel.Result>) {
        list.clear()
        list.addAll( data )
        notifyDataSetChanged()

    }*/