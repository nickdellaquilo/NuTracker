package com.B34.nutracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_first_time_user.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [edit_profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class edit_profile : Fragment() {
    lateinit var userActivityLVL : String

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
            val userGender = genderRadioGroup.checkedRadioButtonId
            val userAge = editAge.text.toString().toIntOrNull()
            val userWeight = editWeight.text.toString().toIntOrNull()
            val userHeight = editHeight.text.toString().toIntOrNull()

            if ((userGender != -1) && (userHeight != null)
                && (userAge != null) && (userWeight != null)) {
                if (userHeight > 250 || userAge > 120 || userWeight > 600) {
                    Toast.makeText(requireContext(), "One or More Invalid Inputs!", Toast.LENGTH_SHORT).show()
                }
                else {
                    // Update User Info in Database

                    val fragment = profile_Fragment()
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.replace(R.id.frame_layout, fragment)?.commit()
                }
            }
            else {
                Toast.makeText(requireContext(), "Fill Out All Inputs!", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}