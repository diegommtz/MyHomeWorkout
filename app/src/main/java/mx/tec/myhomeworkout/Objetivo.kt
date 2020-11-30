package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_genero.*
import kotlinx.android.synthetic.main.activity_objetivo.*
import kotlinx.android.synthetic.main.activity_objetivo.btnNext
import kotlinx.android.synthetic.main.activity_peso.*
import mx.tec.myhomeworkout.services.IPersona
import mx.tec.myhomeworkout.model.Persona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.SQLOutput

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
            val persona = intent.getSerializableExtra("Persona") as? Persona

            if(persona!=null){
                if(btnPerderPeso.isChecked){
                    persona.objetivo = "sRQW9JyHhqDlOBHA8pQQ"
                }else if(btnGanarMusculo.isChecked){
                    persona.objetivo = "qhuIw5EkYioU4ulOj330"
                }else{
                    persona.objetivo = "tZBoZlm08v1w3pYPu9ox"
                }
            }

            val intent = Intent(this@Objetivo, Horario::class.java)
            intent.putExtra("Persona", persona)
            startActivity(intent)



        }

        btnActualizarObjetivo.setOnClickListener{
            //Actualizar objetivo

        }

        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true"){
            btnNext.visibility = View.INVISIBLE
          btnActualizarObjetivo.visibility = View.VISIBLE
        }
    }
}