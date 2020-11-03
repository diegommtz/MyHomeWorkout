package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_genero.*
import kotlinx.android.synthetic.main.activity_objetivo.*
import kotlinx.android.synthetic.main.activity_objetivo.btnNext
import kotlinx.android.synthetic.main.activity_peso.*

class Objetivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)

        // INIT TOGGLE BUTTONS
        btnPerderPeso.setChecked(true)
        btnGanarMusculo.setChecked(false)
        btnEstarEnForma.setChecked(false)

        // ON CLICK
        btnPerderPeso.setOnClickListener {
            btnPerderPeso.setChecked(true)
            btnGanarMusculo.setChecked(false)
            btnEstarEnForma.setChecked(false)
        }
        btnGanarMusculo.setOnClickListener {
            btnPerderPeso.setChecked(false)
            btnGanarMusculo.setChecked(true)
            btnEstarEnForma.setChecked(false)
        }
        btnEstarEnForma.setOnClickListener {
            btnPerderPeso.setChecked(false)
            btnGanarMusculo.setChecked(false)
            btnEstarEnForma.setChecked(true)
        }
        btnNext.setOnClickListener {
            val intent = Intent(this@Objetivo, Horario::class.java)
            startActivity(intent)
        }


        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true"){
            btnNext.visibility = View.INVISIBLE
        }
    }
}