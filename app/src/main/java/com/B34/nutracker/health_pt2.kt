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

class health_pt2 : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_health_pt2, container, false)
        lineChart  = view.findViewById(R.id.line)

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

        if (data == "Vitamin A") {
            lineEntriesList.add(Entry(1f, 890f))
            lineEntriesList.add(Entry(2f, 910f))
            lineEntriesList.add(Entry(3f, 850f))
            lineEntriesList.add(Entry(4f, 970f))
            lineEntriesList.add(Entry(5f, 913f))
            lineEntriesList.add(Entry(6f, 891f))
            lineEntriesList.add(Entry(7f, 900f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Source of Vitamin A: \n" +
            "Sweet potatoes, " +
                    "Carrots, " +
                    "Spinach"

            recEntriesList.add(Entry(1f, 900f))
            recEntriesList.add(Entry(7f, 900f))
        }

        else if (data == "Vitamin B") {
            lineEntriesList.add(Entry(1f, 1.0f))
            lineEntriesList.add(Entry(2f, 1.2f))
            lineEntriesList.add(Entry(3f, 1.3f))
            lineEntriesList.add(Entry(4f, 1.4f))
            lineEntriesList.add(Entry(5f, 2.0f))
            lineEntriesList.add(Entry(6f, 1.7f))
            lineEntriesList.add(Entry(7f, 1.2f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Vitamin B: \n" +
                    "Potato, " + "Fish, " + "Bananas"
            recEntriesList.add(Entry(1f, 1.3f))
            recEntriesList.add(Entry(7f, 1.3f))
        }

        else if (data == "Vitamin C") {
            lineEntriesList.add(Entry(1f, 90f))
            lineEntriesList.add(Entry(2f, 88f))
            lineEntriesList.add(Entry(3f, 95f))
            lineEntriesList.add(Entry(4f, 91f))
            lineEntriesList.add(Entry(5f, 92f))
            lineEntriesList.add(Entry(6f, 92f))
            lineEntriesList.add(Entry(7f, 87f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Vitamin C: \n" +
                    "Pineapple, " +
                    "Broccoli, " +
                    "Tomatoes"

            recEntriesList.add(Entry(1f, 90f))
            recEntriesList.add(Entry(7f, 90f))
        }

        else if (data == "Vitamin D") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Source of Vitamin D: \n" +
            "Fatty Fish, " + "Egg yolks, " + "Mushroom"
            recEntriesList.add(Entry(1f, 15f))
            recEntriesList.add(Entry(7f, 15f))
        }
        else if (data == "Vitamin E") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 12f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Vitamin E: \n" +
            "Spinach, " + "Broccoli, " + "Mangoes"
            recEntriesList.add(Entry(1f, 15f))
            recEntriesList.add(Entry(7f, 15f))
        }

        else if (data == "Vitamin K") {
            lineEntriesList.add(Entry(1f, 110f))
            lineEntriesList.add(Entry(2f, 120f))
            lineEntriesList.add(Entry(3f, 130f))
            lineEntriesList.add(Entry(4f, 104f))
            lineEntriesList.add(Entry(5f, 120f))
            lineEntriesList.add(Entry(6f, 117f))
            lineEntriesList.add(Entry(7f, 121f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Source of Vitamin K: \n" +
                    "Brussels sprouts, " + "Cabbage, " + "Asparagus"
            recEntriesList.add(Entry(1f, 120f))
            recEntriesList.add(Entry(7f, 120f))
        }

        else if (data == "Riboflavin") {
            lineEntriesList.add(Entry(1f, 1.1f))
            lineEntriesList.add(Entry(2f, 1.2f))
            lineEntriesList.add(Entry(3f, 1.3f))
            lineEntriesList.add(Entry(4f, 1.4f))
            lineEntriesList.add(Entry(5f, 1.0f))
            lineEntriesList.add(Entry(6f, 1.7f))
            lineEntriesList.add(Entry(7f, 1.3f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Riboflavin: \n" +
                    "Diary, " + "Whole grains, " + "Lean meats"
            recEntriesList.add(Entry(1f, 1.3f))
            recEntriesList.add(Entry(7f, 1.3f))
        }

        else if (data == "Folate") {
            lineEntriesList.add(Entry(1f, 410f))
            lineEntriesList.add(Entry(2f, 412f))
            lineEntriesList.add(Entry(3f, 430f))
            lineEntriesList.add(Entry(4f, 440f))
            lineEntriesList.add(Entry(5f, 420f))
            lineEntriesList.add(Entry(6f, 417f))
            lineEntriesList.add(Entry(7f, 412f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Source of Folate: \n" +
                    "Citrus Fruit, " + "Beets, " + "Avocado"
            recEntriesList.add(Entry(1f, 400f))
            recEntriesList.add(Entry(7f, 400f))
        }

        else if (data == "Niacin") {
            lineEntriesList.add(Entry(1f, 10f))
            lineEntriesList.add(Entry(2f, 12f))
            lineEntriesList.add(Entry(3f, 13f))
            lineEntriesList.add(Entry(4f, 14f))
            lineEntriesList.add(Entry(5f, 20f))
            lineEntriesList.add(Entry(6f, 17f))
            lineEntriesList.add(Entry(7f, 12f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Niacin: \n" +
                    "Beef, " + "Pork, " + "Chicken"
            recEntriesList.add(Entry(1f, 16f))
            recEntriesList.add(Entry(7f, 16f))
        }

        else if (data == "Choline") {
            lineEntriesList.add(Entry(1f, .4f))
            lineEntriesList.add(Entry(2f, .42f))
            lineEntriesList.add(Entry(3f, .4f))
            lineEntriesList.add(Entry(4f, 5f))
            lineEntriesList.add(Entry(5f, .42f))
            lineEntriesList.add(Entry(6f, .47f))
            lineEntriesList.add(Entry(7f, .42f))

            // Recommended amount
            rec = "Recommended (g)"
            advice = "Good Source of Choline: \n" +
                    "Soy Products, " + "Salmon, " + "Shrimp"
            recEntriesList.add(Entry(1f, .55f))
            recEntriesList.add(Entry(7f, .55f))
        }

        else if (data == "Pantothenic Acid") {
            lineEntriesList.add(Entry(1f, 5.1f))
            lineEntriesList.add(Entry(2f, 5.12f))
            lineEntriesList.add(Entry(3f, 5.4f))
            lineEntriesList.add(Entry(4f, 4.6f))
            lineEntriesList.add(Entry(5f, 5.4f))
            lineEntriesList.add(Entry(6f, 5.7f))
            lineEntriesList.add(Entry(7f, 5.2f))

            // Recommended amount
            rec = "Recommended (mg)"
            advice = "Good Source of Pantothenic Acid: \n" +
                    "Mushrooms, " + "Sweet Potato, " + "Whole grains"
            recEntriesList.add(Entry(1f, 5f))
            recEntriesList.add(Entry(7f, 5f))
        }

        else {
            lineEntriesList.add(Entry(1f, 30f))
            lineEntriesList.add(Entry(2f, 32f))
            lineEntriesList.add(Entry(3f, 30f))
            lineEntriesList.add(Entry(4f, 34f))
            lineEntriesList.add(Entry(5f, 32f))
            lineEntriesList.add(Entry(6f, 37f))
            lineEntriesList.add(Entry(7f, 32f))

            // Recommended amount
            rec = "Recommended (mcg)"
            advice = "Good Source of Biotin: \n" +
                    "Nuts, " + "Organ Meat, " + "Spinach"
            recEntriesList.add(Entry(1f, 30f))
            recEntriesList.add(Entry(7f, 30f))
        }
    }
}