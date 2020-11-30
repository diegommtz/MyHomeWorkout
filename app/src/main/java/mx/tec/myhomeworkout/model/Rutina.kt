package mx.tec.myhomeworkout.model

import java.io.Serializable

class Rutina(
    val idRutina: String,
    val ejercicios: List<Ejercicio>,
    val objetivo: ObjetivoModel
) : Serializable {}