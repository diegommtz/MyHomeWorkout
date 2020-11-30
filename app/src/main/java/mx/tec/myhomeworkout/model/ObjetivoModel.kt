package mx.tec.myhomeworkout.model

import java.io.Serializable

data class Objetivo (
    var idObjetivo: String,
    val nombre: String
) : Serializable {}