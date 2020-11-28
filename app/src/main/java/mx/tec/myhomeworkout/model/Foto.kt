package mx.tec.myhomeworkout.model

import java.io.Serializable
import java.sql.Timestamp

class Foto (
    var idFoto: String,
    var fecha: Timestamp,
    var fotoDerecha: String,
    var fotoFrontal: String,
    var fotoEspalda: String
) : Serializable {}