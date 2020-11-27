package mx.tec.myhomeworkout.model

import java.io.Serializable

class Rutina(val idRutina: Int,
             val ejercicios: List<Ejercicio>,
             val objetivo: String)//Objetivo)
    : Serializable {}