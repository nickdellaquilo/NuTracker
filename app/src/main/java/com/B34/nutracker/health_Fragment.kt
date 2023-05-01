package com.B34.nutracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    lateinit var rec : String
    lateinit var advice : String

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
        recDataSet = LineDataSet(recEntriesList, rec)

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

        val adviceBtn : Button = view.findViewById(R.id.adviceButton)
            adviceBtn.setOnClickListener{
                Toast.makeText(requireContext(), advice, Toast.LENGTH_SHORT).show()
        }


        val check : Button = view.findViewById(R.id.checkButton)
        check.setOnClickListener {
            getLineChartData(data)
            lineDataSet = LineDataSet(lineEntriesList, data)
            recDataSet = LineDataSet(recEntriesList, rec)

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
        rec = "Recommend (g)"

        if (data == "Carbohydrate") {
            lineEntriesList.add(Entry(1f, 310f))
            lineEntriesList.add(Entry(2f, 300f))
            lineEntriesList.add(Entry(3f, 330f))
            lineEntriesList.add(Entry(4f, 300f))
            lineEntriesList.add(Entry(5f, 355f))
            lineEntriesList.add(Entry(6f, 312f))
            lineEntriesList.add(Entry(7f, 300f))

            // Recommended amount
            advice = "Good Source of Carbs: \n" +
                    "Whole grains, Fruits, Vegetables"

            recEntriesList.add(Entry(1f, 320f))
            recEntriesList.add(Entry(7f, 320f))
        }

        else if (data == "Protein") {
            lineEntriesList.add(Entry(1f, 60f))
            lineEntriesList.add(Entry(2f, 72f))
            lineEntriesList.add(Entry(3f, 43f))
            lineEntriesList.add(Entry(4f, 40f))
            lineEntriesList.add(Entry(5f, 70f))
            lineEntriesList.add(Entry(6f, 57f))
            lineEntriesList.add(Entry(7f, 52f))

            // Recommended amount
            advice = "Good Source of Protein: \n" +
                    "Lean meats, Eggs, Dairy products"

            recEntriesList.add(Entry(1f, 50f))
            recEntriesList.add(Entry(7f, 50f))
        }
        else {
            lineEntriesList.add(Entry(1f, 70f))
            lineEntriesList.add(Entry(2f, 82f))
            lineEntriesList.add(Entry(3f, 73f))
            lineEntriesList.add(Entry(4f, 69f))
            lineEntriesList.add(Entry(5f, 63f))
            lineEntriesList.add(Entry(6f, 56f))
            lineEntriesList.add(Entry(7f, 52f))

            // Recommended amount
            advice = "Good Source of Fat: \n" +
                    "Nuts/seeds, Avocado, Fatty fish"

            recEntriesList.add(Entry(1f, 70f))
            recEntriesList.add(Entry(7f, 70f))
        }
    }
}