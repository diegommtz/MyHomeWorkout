package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_genero.*
import kotlinx.android.synthetic.main.activity_profile.*
import mx.tec.myhomeworkout.model.Persona

class Genero : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genero)
        // INIT TOGGLE BUTTONS
        btnMujer.setChecked(true)
        btnHombre.setChecked(false)

        // ON CLICK
        btnMujer.setOnClickListener {
            btnMujer.setChecked(true)
            btnHombre.setChecked(false)
        }
        btnHombre.setOnClickListener {
            btnMujer.setChecked(false)
            btnHombre.setChecked(true)
        }
    }

    fun nacimiento(view: View) {
        val persona = intent.getSerializableExtra("Persona") as? Persona
        val intent = Intent(this@Genero, Nacimiento::class.java)
        if(persona!=null){
            if(btnMujer.isChecked) {
                persona.genero= "Mujer"
                intent.putExtra("Persona", persona)
                startActivity(intent)
            }else {
                persona.genero= "Hombre"
                intent.putExtra("Persona", persona)
                startActivity(intent)
            }
        }

    }
}