package org.carlosalcina.model

import org.bson.codecs.pojo.annotations.BsonId

data class Usuario(
    @BsonId
    val _id: String,
    val nombreCompleto: String,
    val nombreUsuario: String, //unico
    val estado: EstadoUsuario,
    val direccion: Direccion,
    val telefonosContacto: List<String>
)