package mx.tec.myhomeworkout.model

import java.io.Serializable

data class HorarioModel(
    val idHorario: String?,
    val minuto: Int?,
    val hora: Int?,
    val domingo: Boolean?,
    val lunes: Boolean?,
    val martes: Boolean?,
    val miercoles: Boolean?,
    val jueves: Boolean?,
    val viernes: Boolean?,
    val sabado: Boolean?
) : Serializable {}