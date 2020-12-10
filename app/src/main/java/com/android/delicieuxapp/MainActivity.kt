package com.android.delicieuxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btdropdown.setOnClickListener {
          val window = PopupWindow(this)
          val view = layoutInflater.inflate(R.layout.popup_search,null)
          window.contentView = view
          val btsearch = view.findViewById<Button>(R.id.btsearch)
          btsearch.setOnClickListener {
              window.dismiss()
          }
            window.showAtLocation(homepage,
                Gravity.CENTER,0,0)
        }
    }

}
