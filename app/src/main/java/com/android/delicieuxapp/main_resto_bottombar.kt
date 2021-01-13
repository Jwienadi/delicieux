package com.android.delicieuxapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.roughike.bottombar.BottomBar

//import kotlinx.android.synthetic.main.resto_bottombar.*

//import kotlinx.android.synthetic.main.resto_bottombar.*



class RestoMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //get intent parameter
        val RestoID= intent.extras!!.getInt("id")


        super.onCreate(savedInstanceState)
        setContentView(R.layout.resto_bottombar)

        val bottomBar = findViewById<View>(R.id.bottomBar) as? BottomBar
        val firstFragment= FragmentDetailResto()
        //val untuk fragment menu resto
//        val secondFragment=SecondFragment()
        val thirdFragment= FragmentReviewResto()

        //bundle and argument, utk kirim data activity ke fragment
        val args = Bundle()
//        if (RestoID != null) {
        args.putInt("ID", RestoID)
//        }
        firstFragment.setArguments(args)
        thirdFragment.setArguments(args)

        setCurrentFragment(firstFragment)



        bottomBar?.setOnTabSelectListener { tabId ->

            if (tabId == R.id.tab_about) {
                setCurrentFragment(firstFragment)
            }
//            if (tabId == R.id.tab_menu) {
//                setCurrentFragment(secondFragment)
//            }
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