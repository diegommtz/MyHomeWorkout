package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Persona (
    var idPersona: Int?,
    var nombre: String?,
    var contrasena: String?,
    var altura: Int?,
    var genero: String?,
    var nacimiento: String?,
    var objetivo: String?,
    var peso: Float?
) : Serializable {}