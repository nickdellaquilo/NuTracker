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
    lateinit var name : TextView
    lateinit var gender : TextView
    lateinit var height : TextView
    lateinit var age : TextView
    lateinit var weight : TextView
    lateinit var level : TextView

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

        name = view.findViewById<TextView>(R.id.name)
        gender = view.findViewById<TextView>(R.id.gender)
        height = view.findViewById<TextView>(R.id.height)
        age = view.findViewById<TextView>(R.id.age)
        weight = view.findViewById<TextView>(R.id.weight)
        level = view.findViewById<TextView>(R.id.activityLevel)

        initData()
        editBtn.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("name", name.text.toString())

            val fragment = edit_profile()
            val transaction = fragmentManager?.beginTransaction()
            fragment.arguments = bundle
            transaction?.replace(R.id.frame_layout, fragment)?.commit()
        }
        return view
    }
    private fun initData() {
        getData()
    }

    private fun getData() {
        var bundle = arguments
        if (bundle == null) {
            name.text = "Thomas Kong"
            gender.text = "Male"
            height.text = "175 cm"
            age.text = "21 years"
            weight.text = "70 kg"
            level.text = "Little"
        }
        else {
            name.text = bundle!!.getString("name")
            gender.text = bundle!!.getString("gender")
            height.text = bundle!!.getString("height")
            age.text = bundle!!.getString("age")
            weight.text = bundle!!.getString("weight")
            level.text = bundle!!.getString("lvl")
        }
    }
}