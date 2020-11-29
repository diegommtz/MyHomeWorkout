package mx.tec.myhomeworkout.model

import java.io.Serializable

class Ejercicio(
    val idEjercicio: Int,
    val dificultad: String,
    val focalizacion: String,
    val nombre: String,
    val musculos: List<Musculo>
) : Serializable {}