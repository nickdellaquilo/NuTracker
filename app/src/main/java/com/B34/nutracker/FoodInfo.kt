package com.B34.nutracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.B34.nutracker.FoodApi.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodInfo : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var FoodName : TextView;
    private lateinit var ID : TextView;

    private lateinit var addFood : String;
    private val apiKey = "rM6Z3YOJrFX8Oosx5oOYTyya6lFG7kzqcPWxH3wC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_info)

        initData()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Information"

        val addBtn : Button = findViewById(R.id.addButton)
        addBtn.setOnClickListener{
            if (addFood != "null") {
                // ADD TO USER HISTORY
                val intent = Intent(this, HistoryActivity::class.java)
                intent.putExtra("add", addFood)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Invalid Food!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initData() {
        FoodName = findViewById(R.id.FoodName)
        ID = findViewById(R.id.IdNumber)
        getData()
    }

    private fun getData() {
        var intent = intent.extras
        var food = intent!!.getString("food")

        addFood = food.toString()
        FoodName.text = food

        if (addFood != "null") {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.nal.usda.gov/fdc/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val foodApi = retrofit.create(FoodApi::class.java)

            foodApi.searchFood(addFood, apiKey).enqueue(object : Callback<FoodResponse> {
                override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                    if (response.isSuccessful() && response.body() != null) {
                        val foodItems = response.body()?.foods
                        val foodId = foodItems?.get(0)?.fdcId!!.toInt()
                        ID.text = "$foodId"
                        Toast.makeText(this@FoodInfo, "Food Database Central Food Id = $foodId", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                    Toast.makeText(this@FoodInfo, "Error!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}