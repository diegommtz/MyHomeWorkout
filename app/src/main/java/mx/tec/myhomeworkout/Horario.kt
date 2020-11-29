package mx.tec.myhomeworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nacimiento.*
import kotlinx.android.synthetic.main.activity_nacimiento.btnNext
import kotlinx.android.synthetic.main.activity_peso.*
import mx.tec.myhomeworkout.model.Persona

class Horario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)

        btnNext.setOnClickListener {
            val persona = intent.getSerializableExtra("Persona") as? Persona
            val intent = Intent(this@Horario, PrevPhotos::class.java)
            intent.putExtra("Persona", persona)
            startActivity(intent)
        }

        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true") {
            btnNext.visibility = View.INVISIBLE

        }
    }

}