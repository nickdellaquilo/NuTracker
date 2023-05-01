package com.B34.nutracker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_history.*

class search_Fragment : Fragment() {
    lateinit var food_database : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_, container, false)

        val historyBtn : ImageButton = view.findViewById(R.id.btn_history)
        historyBtn.setOnClickListener{
            val intent = Intent (getActivity(), HistoryActivity::class.java)
            intent.putExtra("food","")
            getActivity()?.startActivity(intent)
        }

        val cameraBtn : ImageButton = view.findViewById(R.id.btn_camera)
        cameraBtn.setOnClickListener{
            val intent = Intent (getActivity(), CameraActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        food_database = resources.getStringArray(R.array.food_database).sortedArray()
        val search : SearchView = view.findViewById(R.id.search)
        val listView : ListView = view.findViewById(R.id.searchList)
        val searchAdapter : ArrayAdapter<String> = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1,
            food_database)

        listView.adapter = searchAdapter
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (food_database.contains(p0)) {
                    searchAdapter.filter.filter(p0)
                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                searchAdapter.filter.filter(p0)
                return false
            }
        })

        listView.setOnItemClickListener { parent, view, position, id->
            val selected = parent.getItemAtPosition(position) as String
            val extras = Bundle()
            extras.putString("food", selected)

            val intent = Intent (getActivity(), FoodInfo::class.java)
            intent.putExtras(extras)
            getActivity()?.startActivity(intent)
        }
        return view
    }
}