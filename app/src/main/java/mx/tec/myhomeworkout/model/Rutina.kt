package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Rutina(
    val idRutina: String,
    val nombre: String,
    val ejercicios: List<Ejercicio>,
    val objetivo: Objetivo
) : Serializable {}