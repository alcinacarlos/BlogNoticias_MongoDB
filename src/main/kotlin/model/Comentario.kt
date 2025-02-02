package org.carlosalcina.model

import java.time.LocalDateTime

data class Comentario(
    val nombreUsuario: String,
    val noticiaId: String,
    val texto: String,
    val fechaHora: LocalDateTime
){
    override fun toString(): String {
        return "Usuario: $nombreUsuario, Texto:$texto , Fecha: $fechaHora"
    }
}