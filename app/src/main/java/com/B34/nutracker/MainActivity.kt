package com.B34.nutracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.text.method.LinkMovementMethod
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEmail = findViewById<TextInputEditText>(R.id.et_email)
        val inputPass = findViewById<TextInputEditText>(R.id.et_password)

        val logbtn = findViewById<Button>(R.id.btn_login)
        logbtn.setOnClickListener {
            val userEmail = inputEmail.text.toString()
            val userPass = inputPass.text.toString()

            // Check user email and password match in database
            val userName : String = "Tkong101"
            val password : String = "password123"
            if (userEmail == userName && password == userPass) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Incorrect Email/Password!", Toast.LENGTH_SHORT).show()
            }
        }

        val reg_acc = findViewById<TextView>(R.id.tv_register_acc)
        reg_acc.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
            reg_acc.movementMethod = LinkMovementMethod.getInstance();
        }
    }
}