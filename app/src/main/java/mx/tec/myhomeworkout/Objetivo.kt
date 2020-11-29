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
                    persona.objetivo = "Perder peso"
                }else if(btnGanarMusculo.isChecked){
                    persona.objetivo = "Ganar músculo"
                }else{
                    persona.objetivo = "En forma"
                }
            }

            //Guardar persona en base de datos
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.100.9:3000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(IPersona::class.java)

            service.createPersona(persona).enqueue(object : Callback<Int> {
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    t.message?.let { Log.e("RESTLIBS", it) }
                }

                override fun onResponse(call: Call<Int>, response: retrofit2.Response<Int>) {
                    val intent = Intent(this@Objetivo, Horario::class.java)
                    println("PERSONA")
                    println(response.toString())
                    intent.putExtra("idPersona", response.toString())
                    startActivity(intent)
                }
            })
            //--------------

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