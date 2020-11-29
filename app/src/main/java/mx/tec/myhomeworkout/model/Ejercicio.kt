package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Ejercicio (
    val idEjercicio: Int,
    val dificultad: String,
    val focalizacion: String,
    val nombre: String,
    val musculos: List<Musculo>,
    val cantidadTiempo: Int?,
    val repeticiones: Boolean?
) : Serializable {}