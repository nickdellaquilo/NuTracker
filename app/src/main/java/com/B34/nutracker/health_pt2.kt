package com.B34.nutracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry

class health_pt2 : Fragment() {
    // on below line we are creating
    // variables for our line chart
    lateinit var lineChart: LineChart

    // on below line we are creating
    // a variable for line data
    lateinit var lineData: LineData

    // on below line we are creating a
    // variable for line data set
    lateinit var lineDataSet: LineDataSet

    // on below line we are creating array list for line data
    lateinit var lineEntriesList: ArrayList<Entry>

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

        // on below line we are calling get line
        // chart data to add data to our array list
        getLineChartData()

        // on below line we are initializing our line data set
        lineDataSet = LineDataSet(lineEntriesList, "Line Chart Data")

        // on below line we are initializing our line data
        lineData = LineData(lineDataSet)

        // on below line we are setting data to our line chart
        lineChart.data = lineData

        // on below line we are setting colors for our line chart text
        // lineDataSet.valueTextColor = Color.BLACK

        // on below line we are setting color for our line data set
        lineDataSet.setColor(resources.getColor(R.color.brown_layout))

        // on below line we are setting text size
        lineDataSet.valueTextSize = 16f

        // on below line we are enabling description as false
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

        return view
    }

    private fun getLineChartData() {
        lineEntriesList = ArrayList()

        // on below line we are adding data
        // to our line entries list
        lineEntriesList.add(Entry(1f, 5f))
        lineEntriesList.add(Entry(2f, 8f))
        lineEntriesList.add(Entry(3f, 7f))
        lineEntriesList.add(Entry(4f, 9f))
        lineEntriesList.add(Entry(5f, 10f))
        lineEntriesList.add(Entry(6f, 7f))
        lineEntriesList.add(Entry(7f, 7f))

    }
}