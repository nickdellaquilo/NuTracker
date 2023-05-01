package com.B34.nutracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val inputEmail = findViewById<TextInputEditText>(R.id.et_reg_email)
        val inputPass = findViewById<TextInputEditText>(R.id.et_reg_password)
        val inputPassCheck = findViewById<TextInputEditText>(R.id.et_confirm_password)

        val regBtn = findViewById<Button>(R.id.btn_sign_up)
        regBtn.setOnClickListener {
            val userEmail = inputEmail.text.toString()
            val userPass = inputPass.text.toString()
            val userPassCheck = inputPassCheck.text.toString()

            if (userPass == userPassCheck && userEmail.isNotEmpty() && userPass.isNotEmpty()) {
                val intent = Intent(this, FirstTimeUser::class.java)
                intent.putExtra("email",userEmail)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Invalid Inputs!", Toast.LENGTH_SHORT).show()
            }
        }
        val sign_in = findViewById<TextView>(R.id.tv_sign_in)
        sign_in.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            sign_in.movementMethod = LinkMovementMethod.getInstance();
        }
    }
}