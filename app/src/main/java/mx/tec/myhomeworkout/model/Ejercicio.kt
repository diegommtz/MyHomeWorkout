package mx.tec.myhomeworkout.model

import java.io.Serializable

class Ejercicio(
    val idEjercicio: String,
    val dificultad: String,
    val focalizacion: String,
    val nombre: String,
    val video: String,
    val musculos: List<Musculo>?
) : Serializable {}