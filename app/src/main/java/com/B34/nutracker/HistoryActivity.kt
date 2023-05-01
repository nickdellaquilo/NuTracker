package com.B34.nutracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.B34.nutracker.databinding.ActivityHistoryBinding
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHistoryBinding
    lateinit var foodHistory : Array<String>

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "History"

        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        initData()
        if (foodHistory.isNotEmpty()) {
            adapter = RecyclerAdapter(foodHistory.reversedArray())
            recycler_view.adapter = adapter
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    private fun initData() {
        getData()
    }

    private fun getData() {
        // When Database added getData() will pull from database of
        // User food history
        var intent = intent.extras
        foodHistory = resources.getStringArray(R.array.history)
        val food = (intent!!.getString("add"))
        if (!food.equals(null)) {
            foodHistory += food.toString()
        }
    }
}