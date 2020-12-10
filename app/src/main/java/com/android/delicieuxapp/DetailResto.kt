package com.android.delicieuxapp

import androidx.appcompat.app.AppCompatActivity
//import android.R
import android.os.Bundle
import android.view.View
import com.roughike.bottombar.BottomBar


    class DetailResto : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.detail_resto)
            val bottomBar = findViewById<View>(R.id.bottomBar) as BottomBar
            bottomBar.setOnTabSelectListener { tabId ->
                if (tabId == R.id.tab_About) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                }
            }
        }
    }
