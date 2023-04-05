package com.B34.nutracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.B34.nutracker.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        replaceFragment(search_Fragment())

        binding.bottomNavLayout.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.Profile -> replaceFragment(profile_Fragment())
                R.id.Search-> replaceFragment(search_Fragment())
                R.id.Health -> replaceFragment(health_Fragment())
                else -> {}
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}