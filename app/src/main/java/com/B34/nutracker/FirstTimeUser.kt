package com.B34.nutracker

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.B34.nutracker.databinding.ActivityFirstTimeUserBinding
import kotlinx.android.synthetic.main.activity_first_time_user.*

class FirstTimeUser : AppCompatActivity() {
    lateinit var userActivityLVL : String
    lateinit var userEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_user)

        val editName = findViewById<EditText>(R.id.nameEditText)
        val editHeight = findViewById<EditText>(R.id.heightEditText)
        val editAge = findViewById<EditText>(R.id.ageEditText)
        val editWeight = findViewById<EditText>(R.id.weightEditText)

        val spin : Spinner = findViewById(R.id.activityLevelSpinner)
        if (spin != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.activity_levels))
            spin.adapter = adapter
        }

        userActivityLVL = resources.getStringArray(R.array.activity_levels)[0]
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                userActivityLVL = resources.getStringArray(R.array.activity_levels)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                userActivityLVL = resources.getStringArray(R.array.activity_levels)[0]
            }
        }

        initData()
        val save = findViewById<TextView>(R.id.saveButton)
        save.setOnClickListener {
            val userName = editName.text.toString()
            val genderID = genderRadioGroup.checkedRadioButtonId
            val userAge = editAge.text.toString().toIntOrNull()
            val userWeight = editWeight.text.toString().toIntOrNull()
            val userHeight = editHeight.text.toString().toIntOrNull()

            if (userName.isNotEmpty() && (genderID != -1) && (userHeight != null)
                && (userAge != null) && (userWeight != null)) {
                if (userHeight > 250 || userAge > 120 || userWeight > 600) {
                    Toast.makeText(this, "One or More Invalid Inputs!", Toast.LENGTH_SHORT).show()
                }
                else {
                    // Save to Database new User
                    val userGender : RadioButton = findViewById(genderID)
                    val bundle = Bundle()
                    bundle.putString("name", userName)
                    bundle.putString("gender", userGender.text.toString())
                    bundle.putString("height", editHeight.text.toString() + " cm")
                    bundle.putString("age", editAge.text.toString() + " years")
                    bundle.putString("weight", editWeight.text.toString() + " kg")
                    bundle.putString("lvl", userActivityLVL)

                    val myIntent = Intent(this, HomeActivity::class.java).putExtras(bundle)
                    startActivity(myIntent)
                    }
            }
            else {
                Toast.makeText(this, "Fill Out All Inputs!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initData() {
        getData()
    }

    private fun getData() {
        var intent = intent.extras
        userEmail = intent!!.getString("email").toString()
    }
}