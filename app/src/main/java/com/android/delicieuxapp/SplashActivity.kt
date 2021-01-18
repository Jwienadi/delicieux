package com.android.delicieuxapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if(user != null){
                val dashboardIntent = Intent(this, MainActivity::class.java)
                startActivity(dashboardIntent)
                finish()
            }else{
                val signInIntent = Intent(this, login::class.java)
                startActivity(signInIntent)
                finish()
            }
        }, 2000)
    }
}