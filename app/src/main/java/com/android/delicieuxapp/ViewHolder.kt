package com.android.delicieuxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(inflater : LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_template, parent, false)) {

    private var imgView: ImageView? = null
    private var txtTitle: TextView? = null
    private var txtType: TextView? = null
    private var txtLoc: TextView? = null
    private var txtRat: RatingBar? = null

    init {
        imgView = itemView.findViewById(R.id.img_view)
        txtTitle = itemView.findViewById(R.id.txt_title)
        txtType = itemView.findViewById(R.id.txt_type)
        txtLoc = itemView.findViewById(R.id.txt_loc)
        txtRat = itemView.findViewById(R.id.title_rating)
    }

    fun bind(data: Restaurant){
        imgView?.setImageResource(data.imgView)
        txtTitle?.text = data.txtTitle
        txtType?.text = data.txtType
        txtLoc?.text = data.txtLoc
        txtRat?.rating = data.txtRat.toFloat()

    }

}