package mx.tec.myhomeworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nacimiento.*
import mx.tec.myhomeworkout.model.Persona
import java.util.*

class Nacimiento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nacimiento)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun altura(view: View) {
        val dia = fechaPicker.dayOfMonth.toString()
        val mes = ((fechaPicker.month)+1).toString()
        val anio = fechaPicker.year.toString()
        val persona = intent.getSerializableExtra("Persona") as? Persona
        if (persona != null) {
            persona.nacimiento =("$anio-$mes-$dia")
        }
        val intent = Intent(this@Nacimiento, Altura::class.java)
        intent.putExtra("Persona", persona)
        startActivity(intent)
    }

}