package com.B34.nutracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_first_time_user.*

class edit_profile : Fragment() {
    lateinit var userActivityLVL : String
    lateinit var userName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        val editHeight = view.findViewById<EditText>(R.id.heightEditText)
        val editAge = view.findViewById<EditText>(R.id.ageEditText)
        val editWeight = view.findViewById<EditText>(R.id.weightEditText)

        val spin : Spinner = view.findViewById(R.id.activityLevelSpinner)
        if (spin != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.activity_levels))
            spin.adapter = adapter
        }
        initData()
        userActivityLVL = resources.getStringArray(R.array.activity_levels)[0]
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                userActivityLVL = resources.getStringArray(R.array.activity_levels)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                userActivityLVL = resources.getStringArray(R.array.activity_levels)[0]
            }
        }

        val saveBtn : Button = view.findViewById(R.id.saveButton)
        saveBtn.setOnClickListener{
            val genderID = genderRadioGroup.checkedRadioButtonId
            val userAge = editAge.text.toString().toIntOrNull()
            val userWeight = editWeight.text.toString().toIntOrNull()
            val userHeight = editHeight.text.toString().toIntOrNull()

            if ((genderID != -1) && (userHeight != null)
                && (userAge != null) && (userWeight != null)) {

                val userGender : RadioButton = view.findViewById(genderID)

                if (userHeight > 250 || userAge > 120 || userWeight > 600) {
                    Toast.makeText(requireContext(), "One or More Invalid Inputs!", Toast.LENGTH_SHORT).show()
                }
                else {
                    // Update User Info in Database
                    val bundle = Bundle()
                    bundle.putString("name", userName)
                    bundle.putString("gender", userGender.text.toString())
                    bundle.putString("height", editHeight.text.toString() + " cm")
                    bundle.putString("age", editAge.text.toString() + " years")
                    bundle.putString("weight", editWeight.text.toString() + " kg")
                    bundle.putString("lvl", userActivityLVL)

                    val fragment = profile_Fragment()
                    val transaction = fragmentManager?.beginTransaction()
                    fragment.arguments = bundle
                    transaction?.replace(R.id.frame_layout, fragment)?.commit()
                }
            }
            else {
                Toast.makeText(requireContext(), "Fill Out All Inputs!", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
    private fun initData() {
        getData()
    }

    private fun getData() {
        var bundle = arguments
        userName = bundle!!.getString("name").toString()
    }
}
