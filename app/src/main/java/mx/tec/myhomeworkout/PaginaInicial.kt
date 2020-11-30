package mx.tec.myhomeworkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_pagina_inicial.*
import mx.tec.myhomeworkout.services.IEjercicio
import mx.tec.myhomeworkout.services.IMusculo
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterEntrenador
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterSimpleExercise
import mx.tec.myhomeworkout.elemento.modelo.ElementEntrenador
import mx.tec.myhomeworkout.elemento.modelo.ElementSimpleExercise
import mx.tec.myhomeworkout.model.Ejercicio
import mx.tec.myhomeworkout.model.Musculo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PaginaInicial : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    lateinit var ejercicios: List<Ejercicio>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_inicial)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationCrack)


        val txt =  getString(R.string.estas_a_parte1) + " 3 " + getString(R.string.estas_a_parte2)
        tvWelcomeMessage.setText(txt)

        // EJERCICIOS LIST
        val retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IEjercicio::class.java)
        service.getAllEjercicios().enqueue(object: Callback<List<Ejercicio>> {
            override fun onFailure(call: Call<List<Ejercicio>>, t: Throwable) {
                Log.e("Workout-API", "Error obteniendo datos de ejercicios")
                Log.e("Workout-API", t.message!!)
            }

            override fun onResponse(call: Call<List<Ejercicio>>, response: Response<List<Ejercicio>>) {
                ejercicios = response.body()!!
                Log.e("Workout-API", ejercicios.toString())

                val adaptador = CustomAdapterSimpleExercise(this@PaginaInicial, R.layout.simple_exercise, ejercicios, 0)
                rvLista.layoutManager = LinearLayoutManager(this@PaginaInicial, LinearLayoutManager.HORIZONTAL, false)
                rvLista.adapter = adaptador
            }
        })





        // TRAINERS LIST
        val entrenadores = listOf(
            ElementEntrenador(R.drawable.entrenador_angie, "Angie"),
            ElementEntrenador(R.drawable.entrenador_diego, "Diego"),
            ElementEntrenador(R.drawable.entrenador_fer, "Fer"),
            ElementEntrenador(R.drawable.entrenador_nava, "Nava")
        )
        val adaptador_entrenador = CustomAdapterEntrenador(this@PaginaInicial, R.layout.layout_entrenadores, entrenadores, 0)
        rvLista_entrenadores.layoutManager = LinearLayoutManager(this@PaginaInicial, LinearLayoutManager.HORIZONTAL, false)
        rvLista_entrenadores.adapter = adaptador_entrenador


        //ONLICK LOOK TODAYS RUTINE
        btnPrevis.setOnClickListener {
            val intent = Intent(this@PaginaInicial, PrevisRutina::class.java)
            startActivity(intent)
        }

    }


    //NAVBAR
    private val navigationCrack = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            //TODO : Aqui como paso el id? AIUDA
            //val idPersona = intent.getStringExtra("idPersona")
            R.id.navigation_profileAct -> {
                val intent = Intent(this@PaginaInicial, ProfileAct::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //        Intent.FLAG_ACTIVITY_CLEAR_TASK
                //intent.putExtra("idPersona", idPersona)
                startActivity(intent)
                //finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_monitoreoProgresoGraficas -> {
                val intent = Intent(this@PaginaInicial, MonitoreaProgresoGraficas::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        //Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_paginaInicial -> {

                return@OnNavigationItemSelectedListener false
            }
        }
        false

    }
}