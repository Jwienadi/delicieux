package com.android.delicieuxapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin1.setOnClickListener {
            val email = inputUsername.text.toString()
            val password = inputPassword.text.toString()
            if (email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(this, "Please Insert Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{

                        if (!it.isSuccessful){ return@addOnCompleteListener
                            val intent = Intent (this, login::class.java)
                            startActivity(intent)
                        }
                        else
                            Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent (this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener{
                        Log.d("Main", "Failed Login: ${it.message}")
                        Toast.makeText(this, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
            }
        }

    }
}