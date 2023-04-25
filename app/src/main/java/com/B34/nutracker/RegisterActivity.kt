package com.B34.nutracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regBtn = findViewById<Button>(R.id.btn_sign_up)
        regBtn.setOnClickListener {
            val intent = Intent(this, FirstTimeUser::class.java)
            startActivity(intent)
        }
        val sign_in = findViewById<TextView>(R.id.tv_sign_in)
        sign_in.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            sign_in.movementMethod = LinkMovementMethod.getInstance();
        }
    }
}