package com.B34.nutracker

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.B34.nutracker.databinding.ActivityFirstTimeUserBinding

class FirstTimeUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_user)

        val save = findViewById<TextView>(R.id.saveButton)
        save.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            startActivity(myIntent)
            save.movementMethod = LinkMovementMethod.getInstance();
        }

        val back = findViewById<TextView>(R.id.backButton)
        back.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
            save.movementMethod = LinkMovementMethod.getInstance();
        }
    }
}