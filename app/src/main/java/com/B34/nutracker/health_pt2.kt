package com.B34.nutracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry

class health_pt2 : Fragment() {
    lateinit var lineChart: LineChart // variables for our line chart
    lateinit var lineData: LineData // a variable for line data
    lateinit var lineDataSet: LineDataSet // variable for line data set
    lateinit var lineEntriesList: ArrayList<Entry> // array list for line data
    lateinit var data : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_health_pt2, container, false)
        val lineChart : LineChart = view.findViewById(R.id.line)

        val spin : Spinner = view.findViewById(R.id.VitaminSpinner)
        if (spin != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.Vitamins))
            spin.adapter = adapter
        }

        data = resources.getStringArray(R.array.Vitamins)[0]
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                data = resources.getStringArray(R.array.Vitamins)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                data = resources.getStringArray(R.array.Vitamins)[0]
            }
        }

        getLineChartData(data)
        lineDataSet = LineDataSet(lineEntriesList, data)
        lineData = LineData(lineDataSet)
        lineChart.data = lineData
        lineDataSet.valueTextColor = R.color.black
        lineDataSet.setColor(resources.getColor(R.color.brown_layout))
        lineDataSet.valueTextSize = 0f
        lineChart.description.isEnabled = false

        val btn1 : Button = view.findViewById(R.id.sampleButton)
        btn1.setOnClickListener{
            val fragment = health_Fragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)?.commit()
        }

        val btn3 : Button = view.findViewById(R.id.sampleButton3)
        btn3.setOnClickListener{
            val fragment = health_pt3()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)?.commit()
        }

        val check : Button = view.findViewById(R.id.checkButton)
        check.setOnClickListener {
            lineEntriesList.clear()
            getLineChartData(data)
            lineDataSet = LineDataSet(lineEntriesList, data)
            lineData = LineData(lineDataSet)
            lineChart.data = lineData
            lineDataSet.valueTextColor = R.color.black
            lineDataSet.setColor(resources.getColor(R.color.brown_layout))
            lineDataSet.valueTextSize = 0f
            lineChart.description.isEnabled = false

            lineChart.notifyDataSetChanged()
            lineChart.invalidate()
        }

        return view
    }

    private fun getLineChartData(data : String) {
        lineEntriesList = ArrayList()

        if (data == "Vitamin A") {
            lineEntriesList.add(Entry(1f, 1f))
            lineEntriesList.add(Entry(2f, 2f))
            lineEntriesList.add(Entry(3f, 3f))
            lineEntriesList.add(Entry(4f, 4f))
            lineEntriesList.add(Entry(5f, 5f))
            lineEntriesList.add(Entry(6f, 1f))
            lineEntriesList.add(Entry(7f, 2f))
        }

        else if (data == "Vitamin B") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Vitamin C") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Vitamin D") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }
        else if (data == "Vitamin E") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Vitamin K") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Riboflavin") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Folate") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Niacin") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Choline") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Pantothenic Acid") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else if (data == "Biotin") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))
        }

        else {
            lineEntriesList.add(Entry(1f, 0f))
            lineEntriesList.add(Entry(2f, 2f))
            lineEntriesList.add(Entry(3f, 2f))
            lineEntriesList.add(Entry(4f, 2f))
            lineEntriesList.add(Entry(5f, 3f))
            lineEntriesList.add(Entry(6f, 1f))
            lineEntriesList.add(Entry(7f, 1f))
        }
    }
}