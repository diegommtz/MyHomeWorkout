package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import kotlinx.android.synthetic.main.activity_peso.*
import mx.tec.myhomeworkout.model.Persona

class Peso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        val pesoUnidadesPicker = findViewById<NumberPicker>(R.id.pickerPesoUnidades)
        pesoUnidadesPicker.minValue = 30
        pesoUnidadesPicker.maxValue = 120
        pesoUnidadesPicker.value = 50

        val pesoDecimalesPicker = findViewById<NumberPicker>(R.id.pickerPesoDecimales)
        pesoDecimalesPicker.minValue = 0
        pesoDecimalesPicker.maxValue = 9

        btnNext.setOnClickListener{
            val persona = intent.getSerializableExtra("Persona") as? Persona
            if (persona != null) {
                persona.peso = pesoUnidadesPicker.value.toFloat() + (pesoDecimalesPicker.value).toFloat()/10
            }
            val intent = Intent(this@Peso, Objetivo::class.java)
            intent.putExtra("Persona", persona)
            startActivity(intent)
        }

        btnActualizar.setOnClickListener {
            //Actualizar peso

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
            btnActualizar.visibility = View.VISIBLE
        }
    }
}