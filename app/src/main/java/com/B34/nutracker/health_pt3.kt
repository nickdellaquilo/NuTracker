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

class health_pt3 : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_health_pt3, container, false)
        val lineChart : LineChart = view.findViewById(R.id.line)

        val spin : Spinner = view.findViewById(R.id.MineralSpinner)
        if (spin != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.Minerals))
            spin.adapter = adapter
        }

        data = resources.getStringArray(R.array.Minerals)[0]
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                data = resources.getStringArray(R.array.Minerals)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                data = resources.getStringArray(R.array.Minerals)[0]
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

        val btn1 : Button = view.findViewById(R.id.sampleButton)
        btn1.setOnClickListener{
            val fragment = health_Fragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)?.commit()
        }

        val btn2 : Button = view.findViewById(R.id.sampleButton2)
        btn2.setOnClickListener{
            val fragment = health_pt2()
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

        if (data == "Calcium") {
            lineEntriesList.add(Entry(1f, 1010f))
            lineEntriesList.add(Entry(2f, 1020f))
            lineEntriesList.add(Entry(3f, 993f))
            lineEntriesList.add(Entry(4f, 1004f))
            lineEntriesList.add(Entry(5f, 995f))
            lineEntriesList.add(Entry(6f, 1010f))
            lineEntriesList.add(Entry(7f, 1000f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Calcium: \n" +
                    "Dairy Products, " + "Kale, " + "Tofu"
            recEntriesList.add(Entry(1f, 1000f))
            recEntriesList.add(Entry(7f, 1000f))
        }

        else if (data == "Chloride") {
            lineEntriesList.add(Entry(1f, 2.9f))
            lineEntriesList.add(Entry(2f, 3.2f))
            lineEntriesList.add(Entry(3f, 3.4f))
            lineEntriesList.add(Entry(4f, 3.4f))
            lineEntriesList.add(Entry(5f, 3.2f))
            lineEntriesList.add(Entry(6f, 2.7f))
            lineEntriesList.add(Entry(7f, 2.6f))

            // Recommended amount
            rec = "Recommended (g)"
            advice = "Good Sources of Chloride: \n" +
                    "Seaweed, " + "Olive, " + "Lettuce"
            recEntriesList.add(Entry(1f, 2.3f))
            recEntriesList.add(Entry(7f, 2.3f))
        }

        else if (data == "Chromium") {
            lineEntriesList.add(Entry(1f, 41f))
            lineEntriesList.add(Entry(2f, 62f))
            lineEntriesList.add(Entry(3f, 33f))
            lineEntriesList.add(Entry(4f, 34f))
            lineEntriesList.add(Entry(5f, 30f))
            lineEntriesList.add(Entry(6f, 37f))
            lineEntriesList.add(Entry(7f, 42f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Sources of Chromium: \n" +
                    "Broccoli, " + "Grape Juice, " + "Apple"
            recEntriesList.add(Entry(1f, 35f))
            recEntriesList.add(Entry(7f, 35f))
        }

        else if (data == "Copper") {
            lineEntriesList.add(Entry(1f, 910f))
            lineEntriesList.add(Entry(2f, 920f))
            lineEntriesList.add(Entry(3f, 913f))
            lineEntriesList.add(Entry(4f, 897f))
            lineEntriesList.add(Entry(5f, 920f))
            lineEntriesList.add(Entry(6f, 917f))
            lineEntriesList.add(Entry(7f, 912f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Sources of Cooper: \n" +
                    "Shellfish, " + "Chocolate, " + "Nuts"
            recEntriesList.add(Entry(1f, 900f))
            recEntriesList.add(Entry(7f, 900f))
        }
        else if (data == "Fluoride") {
            lineEntriesList.add(Entry(1f, 4.5f))
            lineEntriesList.add(Entry(2f, 4f))
            lineEntriesList.add(Entry(3f, 3f))
            lineEntriesList.add(Entry(4f, 4f))
            lineEntriesList.add(Entry(5f, 4.2f))
            lineEntriesList.add(Entry(6f, 4.3f))
            lineEntriesList.add(Entry(7f, 4f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Fluoride: \n" +
                    "Grapes, " + "Black Tea, " + "Seafood"
            recEntriesList.add(Entry(1f, 4f))
            recEntriesList.add(Entry(7f, 4f))
        }

        else if (data == "Iodine") {
            lineEntriesList.add(Entry(1f, 140f))
            lineEntriesList.add(Entry(2f, 152f))
            lineEntriesList.add(Entry(3f, 153f))
            lineEntriesList.add(Entry(4f, 145f))
            lineEntriesList.add(Entry(5f, 120f))
            lineEntriesList.add(Entry(6f, 157f))
            lineEntriesList.add(Entry(7f, 142f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Sources of Iodine: \n" +
                    "Seaweed, " + "Dairy Products, " + "Eggs"
            recEntriesList.add(Entry(1f, 150f))
            recEntriesList.add(Entry(7f, 150f))
        }

        else if (data == "Iron") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 8.2f))
            lineEntriesList.add(Entry(3f, 7.3f))
            lineEntriesList.add(Entry(4f, 7.4f))
            lineEntriesList.add(Entry(5f, 8.20f))
            lineEntriesList.add(Entry(6f, 9.7f))
            lineEntriesList.add(Entry(7f, 8f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Iron: \n" +
                    "Red Meat, " + "Fish, " + "Raisin"
            recEntriesList.add(Entry(1f, 8f))
            recEntriesList.add(Entry(7f, 8f))
        }

        else if (data == "Magnesium") {
            lineEntriesList.add(Entry(1f, 410f))
            lineEntriesList.add(Entry(2f, 412f))
            lineEntriesList.add(Entry(3f, 413f))
            lineEntriesList.add(Entry(4f, 414f))
            lineEntriesList.add(Entry(5f, 420f))
            lineEntriesList.add(Entry(6f, 417f))
            lineEntriesList.add(Entry(7f, 412f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Magnesium: \n" +
                    "Whole wheat bread, " + "Nuts, " + "Legumes"
            recEntriesList.add(Entry(1f, 400f))
            recEntriesList.add(Entry(7f, 400f))
        }

        else if (data == "Manganese") {
            lineEntriesList.add(Entry(1f, 1.10f))
            lineEntriesList.add(Entry(2f, 2.1f))
            lineEntriesList.add(Entry(3f, 2.3f))
            lineEntriesList.add(Entry(4f, 2.4f))
            lineEntriesList.add(Entry(5f, 2.0f))
            lineEntriesList.add(Entry(6f, 1.7f))
            lineEntriesList.add(Entry(7f, 1.2f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Manganese: \n" +
                    "Pineapple, " + "Nuts, " + "Tea"
            recEntriesList.add(Entry(1f, 2.3f))
            recEntriesList.add(Entry(7f, 2.3f))
        }

        else if (data == "Molybdenum") {
            lineEntriesList.add(Entry(1f, 44f))
            lineEntriesList.add(Entry(2f, 42f))
            lineEntriesList.add(Entry(3f, 43f))
            lineEntriesList.add(Entry(4f, 44f))
            lineEntriesList.add(Entry(5f, 42f))
            lineEntriesList.add(Entry(6f, 47f))
            lineEntriesList.add(Entry(7f, 42f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Sources of Molybdenum: \n" +
                    "Legumes, " + "Nuts, " + "Spinach"
            recEntriesList.add(Entry(1f, 45f))
            recEntriesList.add(Entry(7f, 45f))
        }

        else if (data == "Phosphorus") {
            lineEntriesList.add(Entry(1f, .7f))
            lineEntriesList.add(Entry(2f, .7f))
            lineEntriesList.add(Entry(3f, .6f))
            lineEntriesList.add(Entry(4f, .7f))
            lineEntriesList.add(Entry(5f, .8f))
            lineEntriesList.add(Entry(6f, .7f))
            lineEntriesList.add(Entry(7f, .7f))

            // Recommended amount
            rec = "Recommended (g)"
            advice = "Good Sources of Phosphorus: \n" +
                    "Dairy Products, " + "Meat, " + "Fish"
            recEntriesList.add(Entry(1f, .7f))
            recEntriesList.add(Entry(7f, .7f))
        }

        else if (data == "Potassium") {
            lineEntriesList.add(Entry(1f, 3410f))
            lineEntriesList.add(Entry(2f, 3412f))
            lineEntriesList.add(Entry(3f, 3413f))
            lineEntriesList.add(Entry(4f, 3414f))
            lineEntriesList.add(Entry(5f, 3400f))
            lineEntriesList.add(Entry(6f, 3390f))
            lineEntriesList.add(Entry(7f, 3400f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Calcium: \n" +
                    "Bananas, " + "Oranges, " + "Tomatoes"
            recEntriesList.add(Entry(1f, 3400f))
            recEntriesList.add(Entry(7f, 3400f))
        }

        else if (data == "Selenium") {
            lineEntriesList.add(Entry(1f, 54f))
            lineEntriesList.add(Entry(2f, 52f))
            lineEntriesList.add(Entry(3f, 53f))
            lineEntriesList.add(Entry(4f, 54f))
            lineEntriesList.add(Entry(5f, 57f))
            lineEntriesList.add(Entry(6f, 57f))
            lineEntriesList.add(Entry(7f, 55f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Sources of Selenium: \n" +
                    "Brazil Nuts, " + "Eggs, " + "Mushrooms"
            recEntriesList.add(Entry(1f, 55f))
            recEntriesList.add(Entry(7f, 55f))
        }

        else if (data == "Sodium") {
            lineEntriesList.add(Entry(1f, 1500f))
            lineEntriesList.add(Entry(2f, 1520f))
            lineEntriesList.add(Entry(3f, 1513f))
            lineEntriesList.add(Entry(4f, 1514f))
            lineEntriesList.add(Entry(5f, 1520f))
            lineEntriesList.add(Entry(6f, 1490f))
            lineEntriesList.add(Entry(7f, 1487f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Sodium: \n" +
                    "Bacon, " + "Cheese, " + "Salted Nuts"
            recEntriesList.add(Entry(1f, 1500f))
            recEntriesList.add(Entry(7f, 1500f))
        }

        else {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 12f))
            lineEntriesList.add(Entry(4f, 12f))
            lineEntriesList.add(Entry(5f, 13f))
            lineEntriesList.add(Entry(6f, 11f))
            lineEntriesList.add(Entry(7f, 11f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Sources of Zinc: \n" +
                    "Red Meats, " + "Whole Grains, " + "Nuts"
            recEntriesList.add(Entry(1f, 11f))
            recEntriesList.add(Entry(7f, 11f))
        }
    }
}