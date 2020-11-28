package mx.tec.myhomeworkout.model

import java.sql.Timestamp

class Foto(
    var idFecha: String,
    var fecha: Timestamp,
    var fotoDerecha: String,
    var fotoFrontal: String,
    var fotoEspalda: String
) {
}