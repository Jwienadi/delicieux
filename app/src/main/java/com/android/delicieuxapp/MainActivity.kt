package com.android.delicieuxapp


import android.R.id
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.roughike.bottombar.BottomBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
