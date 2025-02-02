package org.carlosalcina.model

import org.bson.codecs.pojo.annotations.BsonId
import java.time.LocalDateTime

data class Comentario(
    @BsonId
    val _id: String,
    val nombreUsuario: String,
    val noticiaId: String,
    val texto: String,
    val fechaHora: LocalDateTime
)