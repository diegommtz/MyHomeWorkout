package mx.tec.myhomeworkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_pagina_inicial.*
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterSimpleExercise
import mx.tec.myhomeworkout.elemento.modelo.ElementSimpleExercise


class PaginaInicial : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_inicial)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationCrack)


        val txt =  getString(R.string.estas_a_parte1) + " 3 " + getString(R.string.estas_a_parte2)
        tvWelcomeMessage.setText(txt)

        val datos = listOf(
            ElementSimpleExercise(R.drawable.img_mujer_plancking, "Sentadillas"),
            ElementSimpleExercise(R.drawable.img_mujer_plancking, "Abdominales"),
            ElementSimpleExercise(R.drawable.img_mujer_plancking, "Jumping jacks"),
            ElementSimpleExercise(R.drawable.img_mujer_plancking, "Monster")
        )

        val adaptador = CustomAdapterSimpleExercise(this@PaginaInicial, R.layout.simple_exercise, datos, 0)

        rvLista.layoutManager = LinearLayoutManager(this@PaginaInicial, LinearLayoutManager.HORIZONTAL, false)

        rvLista.adapter = adaptador

        btnPrevis.setOnClickListener {
            val intent = Intent(this@PaginaInicial, PrevisRutina::class.java)
            startActivity(intent)
        }

    }

    private val navigationCrack = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profileAct -> {
                val intent = Intent(this@PaginaInicial, ProfileAct::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //        Intent.FLAG_ACTIVITY_CLEAR_TASK
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