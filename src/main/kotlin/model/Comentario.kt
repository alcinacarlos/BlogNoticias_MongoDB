package org.carlosalcina.model

import java.time.LocalDate


data class Comentario(
    val nombreUsuario: String,
    val noticiaId: String,
    val texto: String,
    val fechaHora: LocalDate
){
    override fun toString(): String {
        return "Usuario: $nombreUsuario, Texto:$texto , Fecha: $fechaHora"
    }
}