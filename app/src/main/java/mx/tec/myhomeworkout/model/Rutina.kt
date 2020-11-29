package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Rutina(
    val idRutina: Int,
    val ejercicios: List<Ejercicio>,
    val objetivo: Objetivo
) : Serializable {}