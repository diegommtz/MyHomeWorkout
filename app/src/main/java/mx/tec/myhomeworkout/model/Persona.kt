package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Persona (
    val idPersona: Int,
    val altura: Float,
    val genero: String,
    val nacimiento: String,
    val objetivo: String,
    val peso: Float
) : Serializable {}