package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import kotlinx.android.synthetic.main.activity_nacimiento.*
import mx.tec.myhomeworkout.model.Persona

class Altura : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_altura)

        val alturaPicker = findViewById<NumberPicker>(R.id.pickerAltura)
        alturaPicker.minValue = 100
        alturaPicker.maxValue = 200
        alturaPicker.value = 150

        btnNext.setOnClickListener {
            val persona = intent.getSerializableExtra("Persona") as? Persona
            if (persona != null) {
                persona.altura = alturaPicker.value
            }
            val intent = Intent(this@Altura, Peso::class.java)
            intent.putExtra("Persona", persona)
            startActivity(intent)
        }

    }

}