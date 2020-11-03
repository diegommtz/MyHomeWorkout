package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_peso.*

class Peso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        val pesoUnidadesPicker = findViewById<NumberPicker>(R.id.pickerPesoUnidades)
        pesoUnidadesPicker.minValue = 30
        pesoUnidadesPicker.maxValue = 120
        pesoUnidadesPicker.value = 150

        val pesoDecimalesPicker = findViewById<NumberPicker>(R.id.pickerPesoDecimales)
        pesoDecimalesPicker.minValue = 0
        pesoDecimalesPicker.maxValue = 9

        btnNext.setOnClickListener{
            val intent = Intent(this@Peso, Objetivo::class.java)
            startActivity(intent)
        }

        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true"){
            one.visibility = View.INVISIBLE
            two.visibility = View.INVISIBLE
            three.visibility = View.INVISIBLE
            four.visibility = View.INVISIBLE
            tvDescripcion.visibility = View.INVISIBLE
            btnNext.visibility = View.INVISIBLE

        }
    }
}