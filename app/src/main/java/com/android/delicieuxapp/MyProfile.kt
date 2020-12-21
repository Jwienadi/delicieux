package com.android.delicieuxapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.roughike.bottombar.BottomBar

class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.myprofile);

        val bottomBar = findViewById<View>(R.id.bottomBar) as BottomBar
        bottomBar.setOnTabSelectListener { tabId ->
            if (tabId == R.id.tab_search) {
                setContentView(R.layout.activity_main)
            } else if (tabId == R.id.tab_profile) {
                setContentView(R.layout.myprofile)
            }
        }
    }
}