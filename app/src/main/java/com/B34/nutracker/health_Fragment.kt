package com.B34.nutracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class health_Fragment : Fragment() {
    lateinit var lineChart: LineChart // variables for our line chart
    lateinit var lineData: LineData // a variable for line data

    lateinit var lineDataSet: LineDataSet // variable for line data set
    lateinit var recDataSet: LineDataSet // variable for rec line data set

    lateinit var lineEntriesList: ArrayList<Entry> // array list for user nutrients
    lateinit var recEntriesList: ArrayList<Entry> // array list for recommended compare
    lateinit var dataSets : ArrayList<ILineDataSet> // array list for recommended compare

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
        val view = inflater.inflate(R.layout.fragment_health_, container, false)
        lineChart = view.findViewById(R.id.line)

        val spin : Spinner = view.findViewById(R.id.MacroNutrientSpinner)
        if (spin != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.Macronutrients))
            spin.adapter = adapter
        }

        data = resources.getStringArray(R.array.Macronutrients)[0]
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                data = resources.getStringArray(R.array.Macronutrients)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                data = resources.getStringArray(R.array.Macronutrients)[0]
            }
        }

        getLineChartData(data)
        lineDataSet = LineDataSet(lineEntriesList, data)
        recDataSet = LineDataSet(recEntriesList, "Recommended")

        dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)
        dataSets.add(recDataSet)

        lineData = LineData(dataSets)
        lineChart.data = lineData

        lineDataSet.setColor(resources.getColor(R.color.dark_brown))
        recDataSet.setColor(resources.getColor(R.color.brown_layout))
        lineDataSet.lineWidth = 1.5f
        recDataSet.lineWidth = 3f

        lineDataSet.valueTextSize = 0f
        recDataSet.valueTextSize = 0f
        lineChart.description.isEnabled = false

        val btn2 : Button = view.findViewById(R.id.sampleButton2)
        btn2.setOnClickListener{
            val fragment = health_pt2()
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
            getLineChartData(data)
            lineDataSet = LineDataSet(lineEntriesList, data)
            recDataSet = LineDataSet(recEntriesList, "Recommended")

            dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)
            dataSets.add(recDataSet)

            lineData = LineData(dataSets)
            lineChart.data = lineData

            lineDataSet.setColor(resources.getColor(R.color.dark_brown))
            recDataSet.setColor(resources.getColor(R.color.brown_layout))
            lineDataSet.lineWidth = 1.5f
            recDataSet.lineWidth = 3f

            lineDataSet.valueTextSize = 0f
            recDataSet.valueTextSize = 0f
            lineChart.description.isEnabled = false

            lineChart.notifyDataSetChanged()
            lineChart.invalidate()
        }

        return view
    }

    private fun getLineChartData(data : String) {
        lineEntriesList = ArrayList()
        recEntriesList = ArrayList()

        if (data == "Carbohydrate") {
            lineEntriesList.add(Entry(1f, 1f))
            lineEntriesList.add(Entry(2f, 2f))
            lineEntriesList.add(Entry(3f, 3f))
            lineEntriesList.add(Entry(4f, 4f))
            lineEntriesList.add(Entry(5f, 5f))
            lineEntriesList.add(Entry(6f, 1f))
            lineEntriesList.add(Entry(7f, 2f))

            // Recommended amount
            recEntriesList.add(Entry(1f, 3f))
            recEntriesList.add(Entry(7f, 3f))
        }

        else if (data == "Protein") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))

            // Recommended amount
            recEntriesList.add(Entry(1f, 3f))
            recEntriesList.add(Entry(7f, 3f))
        }
        else {
            lineEntriesList.add(Entry(1f, 0f))
            lineEntriesList.add(Entry(2f, 2f))
            lineEntriesList.add(Entry(3f, 2f))
            lineEntriesList.add(Entry(4f, 2f))
            lineEntriesList.add(Entry(5f, 3f))
            lineEntriesList.add(Entry(6f, 1f))
            lineEntriesList.add(Entry(7f, 1f))

            // Recommended amount
            recEntriesList.add(Entry(1f, 3f))
            recEntriesList.add(Entry(7f, 3f))
        }
    }
}