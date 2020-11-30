package mx.tec.myhomeworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_previs_rutina.*
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterEjercicioRutina
import mx.tec.myhomeworkout.model.Persona
import mx.tec.myhomeworkout.services.IRutina
import mx.tec.myhomeworkout.model.Rutina
import mx.tec.myhomeworkout.services.IPersona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class PrevisRutina : AppCompatActivity() {
    var idRutina: String = "4SYtfbDJKAlZ3SlfoR0r"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previs_rutina)

        // Asignar Dia
        var dia = getToday()
        tvDia.setText(dia)

        // OBTENER RUTINA
        val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
        val idUsuario = sp.getString("idUsuario", "")
        var idObjetivo: String


        val retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val servicePersona = retrofit.create(IPersona::class.java)
        servicePersona.getPersona(idUsuario).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>, response: Response<Persona>) {
                val persona = response.body()
                idObjetivo = persona?.objetivo!!.idObjetivo

                if(idObjetivo == "qhuIw5EkYioU4ulOj330")
                    idRutina = "4SYtfbDJKAlZ3SlfoR0r"
                if(idObjetivo == "sRQW9JyHhqDlOBHA8pQQ")
                    idRutina = "4SYtfbDJKAlZ3SlfoR0r"
                if(idObjetivo == "sRQW9JyHhqDlOBHA8pQQ")
                    idRutina = "4SYtfbDJKAlZ3SlfoR0r"

                val service = retrofit.create(IRutina::class.java)
                service.getRutinaById(idRutina).enqueue(object: Callback<Rutina> {
                    override fun onFailure(call: Call<Rutina>, t: Throwable) {
                        Log.e("Workout-API", "Error obteniendo datos de ejercicios")
                        Log.e("Workout-API", t.message!!)
                    }
                    override fun onResponse(call: Call<Rutina>, response: Response<Rutina>) {
                        var rutina = response.body()!!
                        Log.e("Workout-API", rutina.toString())
                        Log.e("Workout-API", rutina.objetivo.nombre.toString())
                        Log.e("Workout-API", rutina.ejercicios[1].toString())
                        Log.e("Workout-API", rutina.ejercicios[1].musculos?.get(0)?.nombre.toString())

                        tvNombreRutina.setText(rutina.nombre)
                        tvTiempoEstimado.setText(rutina.tiempoEstimado + " minutos")

                        var ejerciciosRutina = rutina.ejercicios
                        val adaptador = CustomAdapterEjercicioRutina(this@PrevisRutina, R.layout.layout_ejercicio_rutina, ejerciciosRutina, 0, layoutInflater)
                        rvEjerciciosRutina.layoutManager = LinearLayoutManager(this@PrevisRutina, LinearLayoutManager.VERTICAL, false)
                        rvEjerciciosRutina.adapter = adaptador

                        val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
                        with(sp.edit()) {
                            putString("idRutina", rutina.idRutina)
                            putString("rutinaNombre", rutina.nombre)
                            putString("rutinaEjerciciosSize", rutina.ejercicios.size.toString())
                            commit()
                        }
                    }
                })
            }
            override fun onFailure(call: Call<Persona>, t: Throwable) {
                t.message?.let { Log.e("RESTLIBS", it) }
            }
        })


        comenzarRutina.setOnClickListener {
            val intent = Intent(this@PrevisRutina, HaciendoEjercicio::class.java)
            startActivity(intent)
        }
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