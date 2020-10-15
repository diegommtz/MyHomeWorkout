package mx.tec.myhomeworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_grafica.*

class Grafica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafica)

        setBarChart()
    }

    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))

        val barDataSet = BarDataSet(entries, "IMC")

        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        val data = BarData(labels, barDataSet)
        barChart.data = data // set the data and list of lables into chart

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barChart.animateY(5000)
    }
}