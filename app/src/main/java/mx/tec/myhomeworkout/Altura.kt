package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import kotlinx.android.synthetic.main.activity_nacimiento.*

class Altura : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_altura)

        val alturaPicker = findViewById<NumberPicker>(R.id.pickerAltura)
        alturaPicker.minValue = 100
        alturaPicker.maxValue = 200
        alturaPicker.value = 150

        btnNext.setOnClickListener {
            val intent = Intent(this@Altura, Peso::class.java)
            startActivity(intent)
        }

    }

}