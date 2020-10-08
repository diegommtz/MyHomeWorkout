package mx.tec.myhomeworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker

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
    }
}