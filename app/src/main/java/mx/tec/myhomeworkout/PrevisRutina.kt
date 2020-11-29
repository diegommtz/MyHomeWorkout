package mx.tec.myhomeworkout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pagina_inicial.*
import kotlinx.android.synthetic.main.activity_previs_rutina.*
import mx.tec.myhomeworkout.data.IEjercicio
import mx.tec.myhomeworkout.data.IRutina
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterParent
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterSimpleExercise
import mx.tec.myhomeworkout.elemento.modelo.ElementChild
import mx.tec.myhomeworkout.elemento.modelo.ElementParent
import mx.tec.myhomeworkout.model.Ejercicio
import mx.tec.myhomeworkout.model.Rutina
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class PrevisRutina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previs_rutina)

        // Asignar Dia
        var dia = getToday()
        tvDia.setText(dia)

        // OBTENER RUTINA
        var idRutina = "4SYtfbDJKAlZ3SlfoR0r"
        val retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IRutina::class.java)
        service.getRutinaById(idRutina).enqueue(object: Callback<Rutina> {
            override fun onFailure(call: Call<Rutina>, t: Throwable) {
                Log.e("Workout-API", "Error obteniendo datos de rutina")
                Log.e("Workout-API", t.message!!)
            }
            override fun onResponse(call: Call<Rutina>, response: Response<Rutina>) {
                var rutina = response.body()!!
                Log.e("Workout-API", rutina.toString())
                Toast.makeText(this@PrevisRutina, "ALOO", Toast.LENGTH_SHORT).show()
            }
        })

        comenzarRutina.setOnClickListener {
            val intent = Intent(this@PrevisRutina, HaciendoEjercicio::class.java)
            startActivity(intent)
        }

        val dataChild1 = listOf(
            ElementChild("Squats", R.drawable.img_mujer_plancking, "10 repeticiones"),
            ElementChild("Plancking", R.drawable.img_mujer_plancking, "2 min"),
            ElementChild("Sentadillas", R.drawable.img_mujer_plancking, "25 repeticiones")
        )

        val dataChild2 = listOf(
            ElementChild("Pesas", R.drawable.img_mujer_plancking, "10 repeticiones"),
            ElementChild("Abdominales", R.drawable.img_mujer_plancking, "25 repeticiones"),
            ElementChild("Abdominales bajas", R.drawable.img_mujer_plancking, "25 repeticiones")
        )

        val dataParent = listOf(
            ElementParent("Circuito 1", "3 sets", "10 min descanso", dataChild1),
            ElementParent("Circuito 2", "4 sets", "15 min descanso", dataChild2)
        )

        val adaptador = CustomAdapterParent(
            this@PrevisRutina,
            R.layout.layout_parent,
            dataParent,
            0
        )

        rvParent.layoutManager = LinearLayoutManager(
            this@PrevisRutina,
            LinearLayoutManager.VERTICAL,
            false
        )

        rvParent.adapter = adaptador
    }

    fun getToday(): String{
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        when (day) {
            Calendar.SUNDAY -> {
                return "Domingo"
            }
            Calendar.MONDAY -> {
                return "Lunes"
            }
            Calendar.TUESDAY -> {
                return "Martes"
            }
            Calendar.WEDNESDAY -> {
                return "Miercoles"
            }
            Calendar.THURSDAY -> {
                return "Jueves"
            }
            Calendar.FRIDAY -> {
                return "Viernes"
            }
        }
            return "Sábado"
        }
}