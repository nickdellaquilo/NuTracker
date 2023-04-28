package com.B34.nutracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class profile_Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_, container, false)
        val editBtn : Button = view.findViewById(R.id.editButton)

        val name = view.findViewById<TextView>(R.id.name)
        val gender = view.findViewById<TextView>(R.id.gender)
        val height = view.findViewById<TextView>(R.id.height)
        val age = view.findViewById<TextView>(R.id.age)
        val weight = view.findViewById<TextView>(R.id.weight)
        val level = view.findViewById<TextView>(R.id.activityLevel)

        // Get Info From Database

        // test data
        name.text = "John Doe"
        gender.text = "Female"
        height.text = "175 cm"
        age.text = "25 years"
        weight.text = "70 kg"
        level.text = "High"


        editBtn.setOnClickListener{
            val fragment = edit_profile()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)?.commit()
        }
        return view
    }
}