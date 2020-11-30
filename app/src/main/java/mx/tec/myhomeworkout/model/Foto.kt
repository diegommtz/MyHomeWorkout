package mx.tec.myhomeworkout.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.sql.Timestamp

data class Foto (
    var idFoto: String,
    var fecha: JsonObject?,
    var fotoDerecha: String,
    var fotoFrontal: String,
    var fotoEspalda: String
) : Serializable {}