package com.android.delicieuxapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.roughike.bottombar.BottomBar

//import kotlinx.android.synthetic.main.resto_bottombar.*

//import kotlinx.android.synthetic.main.resto_bottombar.*


//masih nyoba2, buat bottombar resto
class RestoMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resto_bottombar)

        val bottomBar = findViewById<View>(R.id.bottomBar) as? BottomBar
        val firstFragment=FragmentDetailResto()
//        val secondFragment=SecondFragment()
        val thirdFragment=FragmentReviewResto()

        setCurrentFragment(firstFragment)



        bottomBar?.setOnTabSelectListener { tabId ->

            if (tabId == R.id.tab_about) {
                setCurrentFragment(firstFragment)
            }
            if (tabId == R.id.tab_review) {
                setCurrentFragment(thirdFragment)
            }
        }
//        bottomBar.setOnTabSelectListener { tabId ->
//            when(tabId){
//                R.id.tab_About->setCurrentFragment(firstFragment)
////                R.id.person->setCurrentFragment(secondFragment)
//                R.id.tab_review->setCurrentFragment(thirdFragment)
//
//            }
//            true
//        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}