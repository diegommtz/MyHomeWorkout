package mx.tec.myhomeworkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_pagina_inicial.*
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterSimpleExercise
import mx.tec.myhomeworkout.elemento.modelo.ElementSimpleExercise

class PaginaInicial : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_inicial)

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
}