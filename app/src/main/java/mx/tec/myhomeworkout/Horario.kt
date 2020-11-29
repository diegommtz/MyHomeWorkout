package mx.tec.myhomeworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nacimiento.*
import kotlinx.android.synthetic.main.activity_nacimiento.btnNext
import kotlinx.android.synthetic.main.activity_peso.*

class Horario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val idPersona = intent.getStringExtra("idPersona")
        println("idPersona en horario")
        println(idPersona)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)

        btnNext.setOnClickListener {
            val intent = Intent(this@Horario, PrevPhotos::class.java)
            intent.putExtra("idPersona", idPersona)

            startActivity(intent)
        }


        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true") {
            btnNext.visibility = View.INVISIBLE

        }
    }

}