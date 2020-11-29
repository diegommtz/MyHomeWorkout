package mx.tec.myhomeworkout.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_pagina_inicial.*
import mx.tec.myhomeworkout.*
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterSimpleExercise
import mx.tec.myhomeworkout.elemento.modelo.ElementSimpleExercise

class PaginaInicialFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment_marco_espalda
        val view= inflater.inflate(R.layout.activity_pagina_inicial, container, false)

        val tvWelcomeMessage=view.findViewById<TextView>(R.id.tvWelcomeMessage)
        val txt =  getString(R.string.estas_a_parte1) + " 3 " + getString(R.string.estas_a_parte2)
        tvWelcomeMessage.setText(txt)

        val btnPrevis=view.findViewById<Button>(R.id.btnPrevis)
        val rvLista=view.findViewById<RecyclerView>(R.id.rvLista)



        btnPrevis.setOnClickListener {
            val intent = Intent(context, PrevisRutina::class.java)
            startActivity(intent)
        }


        return view
    }

}