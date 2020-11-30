package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Rutina(
    val idRutina: String,
    val ejercicios: List<Ejercicio>,
    val nombre: String,
    val tiempoEstimado: String,
    val objetivo: Objetivo
) : Serializable {}