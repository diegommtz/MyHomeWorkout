package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Ejercicio(
    val idEjercicio: String,
    val dificultad: String,
    val focalizacion: String,
    val nombre: String,
    val video: String,
    val foto: String,
    val musculos: List<Musculo>?,
    val cantidadTiempo: Int?,
    val repeticiones: Boolean?
) : Serializable {}