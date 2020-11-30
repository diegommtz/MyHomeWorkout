package mx.tec.myhomeworkout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_monitorea_progreso_fotos.*
import kotlinx.android.synthetic.main.activity_pagina_inicial.*
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterFoto
import mx.tec.myhomeworkout.model.Foto
import mx.tec.myhomeworkout.services.IFoto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MonitoreaProceso : AppCompatActivity() {

    lateinit var fotos: List<Foto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitorea_progreso_fotos)

        //val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
        //val idUsuario = sp.getString("idUsuario", "ERROR")!!

        val idUsuario = "pGEn22yIclf6ZSqGB7YR";


        // FOTOS LIST
        val retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IFoto::class.java)
        service.GetFotosPersona(idUsuario).enqueue(object: Callback<List<Foto>> {
            override fun onFailure(call: Call<List<Foto>>, t: Throwable) {
                Log.e("Workout-API", "Error obteniendo fotos de persona")
                Log.e("Workout-API", t.message!!)
            }

            override fun onResponse(call: Call<List<Foto>>, response: Response<List<Foto>>) {
                fotos = response.body()!!
                val adaptador = CustomAdapterFoto(this@MonitoreaProceso, R.layout.layout_fotos, fotos, 0)
                rvFotos.layoutManager = LinearLayoutManager(this@MonitoreaProceso, LinearLayoutManager.VERTICAL, false);
                rvFotos.adapter = adaptador
            }
        })

    }

    private val navigationCrack = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profileAct -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_monitoreoProgresoGraficas -> {
                val intent = Intent(this@MonitoreaProceso, MonitoreaProgresoGraficas::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_paginaInicial -> {
                val intent = Intent(this@MonitoreaProceso, PaginaInicial::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                //finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

}