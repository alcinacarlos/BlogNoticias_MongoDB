package org.carlosalcina.model

import org.bson.codecs.pojo.annotations.BsonId
import java.time.LocalDate

data class Noticia(
    @BsonId
    val titulo: String,
    val cuerpo: String,
    val fechaPublicacion: LocalDate,
    val autor: String,
    val etiquetas: List<String>? = null
){
    override fun toString(): String {
        return "Título: $titulo, Cuerpo: $cuerpo, FechaPub: $fechaPublicacion, Autor: $autor, Etiquetas: $etiquetas"
    }
}