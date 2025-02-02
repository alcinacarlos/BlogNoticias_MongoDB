package org.carlosalcina.model

import org.bson.codecs.pojo.annotations.BsonId
import java.time.LocalDateTime

data class Noticia(
    @BsonId
    val _id: String,
    val titulo: String,
    val cuerpo: String,
    val fechaPublicacion: LocalDateTime,
    val autor: Usuario,
    val etiquetas: List<String>? = null
)