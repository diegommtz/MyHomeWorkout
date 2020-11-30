package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Persona (
    var idPersona: String?,
    var nombre: String?,
    var contrasena: String?,
    var altura: Int?,
    var genero: String?,
    var nacimiento: String?,
    var objetivo: Objetivo?,
    var peso: Float?,
    var entrenamientos: Int?
) : Serializable {}