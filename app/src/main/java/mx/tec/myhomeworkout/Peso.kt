package mx.tec.myhomeworkout

import android.R.attr.x
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_objetivo.*
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_peso.btnNext
import mx.tec.myhomeworkout.model.Objetivo
import mx.tec.myhomeworkout.model.Persona
import mx.tec.myhomeworkout.services.IPersona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Peso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        val pesoUnidadesPicker = findViewById<NumberPicker>(R.id.pickerPesoUnidades)
        pesoUnidadesPicker.minValue = 30
        pesoUnidadesPicker.maxValue = 120
        pesoUnidadesPicker.value = 50

        val pesoDecimalesPicker = findViewById<NumberPicker>(R.id.pickerPesoDecimales)
        pesoDecimalesPicker.minValue = 0
        pesoDecimalesPicker.maxValue = 9

        btnNext.setOnClickListener{
            val persona = intent.getSerializableExtra("Persona") as? Persona
            if (persona != null) {
                persona.peso = pesoUnidadesPicker.value.toFloat() + (pesoDecimalesPicker.value).toFloat()/10
            }
            val intent = Intent(this@Peso, ObjetivoAct::class.java)
            intent.putExtra("Persona", persona)
            startActivity(intent)
        }

        btnOkPeso.setOnClickListener{

            //----------------
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(IPersona::class.java)
            val persona = intent.getSerializableExtra("personaObjetivo") as? Persona

            persona?.peso = pesoUnidadesPicker.value.toFloat() + (pesoDecimalesPicker.value).toFloat() / 10

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
                            this@Peso,
                            "Se actualizó la información",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@Peso, ProfileAct::class.java)
                        startActivity(intent)
                    }
                })
            }

            //------------------
            //-----------------
        }
        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")

        if (invisible == "true"){
            val persistPeso = intent.getFloatExtra("persistPeso", 0f)
            var floatDecimales : Float
            floatDecimales = (persistPeso - (persistPeso.toInt()))*10
            pesoUnidadesPicker.value = persistPeso.toInt()
            pesoDecimalesPicker.value = floatDecimales.toInt()

            one.visibility = View.INVISIBLE
            two.visibility = View.INVISIBLE
            three.visibility = View.INVISIBLE
            four.visibility = View.INVISIBLE
            tvDescripcion.visibility = View.INVISIBLE
            btnNext.visibility = View.INVISIBLE
            btnOkPeso.visibility = View.VISIBLE
        }
    }
}