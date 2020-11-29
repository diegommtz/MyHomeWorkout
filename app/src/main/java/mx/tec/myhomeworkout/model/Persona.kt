package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Persona (
    val idPersona: Int?,
    var nombre: String?,
    var contrasenia: String?,
    val altura: Float?,
    val genero: String?,
    val nacimiento: String?,
    val objetivo: Objetivo?,
    val peso: Float?
) : Serializable {}