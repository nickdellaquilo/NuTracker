package com.B34.nutracker

import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.B34.nutracker.databinding.ActivityFoodInfoBinding
import com.B34.nutracker.databinding.ActivityHistoryBinding
import kotlinx.android.synthetic.main.activity_history.*
import org.w3c.dom.Text

class FoodInfo : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFoodInfoBinding
    private lateinit var FoodName : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_info)
        initData()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Information"

        val editQuantity = findViewById<EditText>(R.id.Quantity)
        val addBtn : Button = findViewById(R.id.addButton)
        addBtn.setOnClickListener{
            val userQuantity= editQuantity.text.toString().toIntOrNull()
            if (userQuantity != null) {
                // ADD TO USER HISTORY
                val intent = Intent(this, HistoryActivity::class.java)
                intent.putExtra("add", FoodName.toString())
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Invalid Quantity!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initData() {
        FoodName = findViewById(R.id.FoodName)
        getData()
    }

    private fun getData() {
        var intent = intent.extras
        var food = intent!!.getString("food")
        FoodName.text = food
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}