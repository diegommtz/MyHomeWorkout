package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_genero.*
import kotlinx.android.synthetic.main.activity_objetivo.*
import kotlinx.android.synthetic.main.activity_objetivo.btnNext
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_profile.*
import mx.tec.myhomeworkout.model.HorarioModel
import mx.tec.myhomeworkout.model.Objetivo
import mx.tec.myhomeworkout.services.IPersona
import mx.tec.myhomeworkout.model.Persona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ObjetivoAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)
        var objetivo : Objetivo


        // INIT TOGGLE BUTTONS
        btnPerderPeso.setChecked(true)
        btnGanarMusculo.setChecked(false)
        btnEstarEnForma.setChecked(false)
        lateinit var miObjetivo:String

        // ON CLICK
        btnPerderPeso.setOnClickListener {
            btnPerderPeso.setChecked(true)
            btnGanarMusculo.setChecked(false)
            btnEstarEnForma.setChecked(false)
            miObjetivo="PerderPeso"
        }
        btnGanarMusculo.setOnClickListener {
            btnPerderPeso.setChecked(false)
            btnGanarMusculo.setChecked(true)
            btnEstarEnForma.setChecked(false)
            miObjetivo="GanarMusculo"
        }
        btnEstarEnForma.setOnClickListener {
            btnPerderPeso.setChecked(false)
            btnGanarMusculo.setChecked(false)
            btnEstarEnForma.setChecked(true)
            miObjetivo="EstarEnForma"
        }
        btnNext.setOnClickListener {
            val persona = intent.getSerializableExtra("Persona") as? Persona
            //val tObjetivo = ObjetivoModel("0",miObjetivo)
            if(persona!=null){
                if(btnPerderPeso.isChecked){
                    objetivo = Objetivo("sRQW9JyHhqDlOBHA8pQQ", "")
                }else if(btnGanarMusculo.isChecked){
                    objetivo = Objetivo("qhuIw5EkYioU4ulOj330", "")
                }else{
                    objetivo = Objetivo("tZBoZlm08v1w3pYPu9ox", "")
                }
                persona.objetivo = objetivo
            }

            val intent = Intent(this@ObjetivoAct, Horario::class.java)
            intent.putExtra("Persona", persona)
            //intent.putExtra("Objetivo",tObjetivo)
            startActivity(intent)
        }

        btnOkObjetivo.setOnClickListener{
            //------------------
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(IPersona::class.java)
            val persona = intent.getSerializableExtra("personaObjetivo") as? Persona

            if(btnPerderPeso.isChecked){
                objetivo = Objetivo("sRQW9JyHhqDlOBHA8pQQ", "")
            }else if(btnGanarMusculo.isChecked){
                objetivo = Objetivo("qhuIw5EkYioU4ulOj330", "")
            }else{
                objetivo = Objetivo("tZBoZlm08v1w3pYPu9ox", "")
            }
            persona?.objetivo = objetivo

            if (persona != null) {
                service.updatePersona(persona).enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.message?.let { Log.e("RESTLIBS", it) }
                    }

                    override fun onResponse(
                        call: Call<String>,
                        response: retrofit2.Response<String>
                    ) {
                        Toast.makeText(
                            this@ObjetivoAct,
                            "Se actualizó la información",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@ObjetivoAct, ProfileAct::class.java)
                        startActivity(intent)
                    }
                })
            }

            //------------------

        }
        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true"){
            val persistObjetivo = intent.getStringExtra("persistObjetivo")

            if(persistObjetivo.equals("Ganar músculo")){
                btnGanarMusculo.isChecked = true
                btnPerderPeso.isChecked = false
                btnEstarEnForma.isChecked = false
            }else if(persistObjetivo.equals("Perder peso")){
                btnGanarMusculo.isChecked = false
                btnPerderPeso.isChecked = true
                btnEstarEnForma.isChecked = false
            }else{
                btnGanarMusculo.isChecked = false
                btnPerderPeso.isChecked = false
                btnEstarEnForma.isChecked = true
            }
            btnNext.visibility = View.INVISIBLE
            btnOkObjetivo.visibility = View.VISIBLE
        }
    }
}