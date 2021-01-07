package com.android.delicieuxapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var title: List<String>, private var jenis: List<String>,
                      private var locations: List<String>,
                       private var rating: List<Double>,
                      private var image: List<Int>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.txt_title)
        val itemType: TextView = itemView.findViewById(R.id.txt_type)
        val itemLoc: TextView = itemView.findViewById(R.id.txt_loc)
        val itemRat: RatingBar = itemView.findViewById(R.id.tv_rating)
        val itemPicture : ImageView =  itemView.findViewById(R.id.img_view)

        init{
            itemView.setOnClickListener {v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,"You Clicked On Item # ${position+1}", Toast.LENGTH_SHORT).show()
            }
        }
                           }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.itemTitle.text = title[position]
        holder.itemType.text = jenis[position]
        holder.itemLoc.text = locations[position]
        holder.itemRat.rating = rating[position].toFloat()
        holder.itemPicture.setImageResource(image[position])
    }

    override fun getItemCount(): Int {
        return title.size
    }
}

