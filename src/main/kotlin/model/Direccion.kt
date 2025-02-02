package org.carlosalcina.model


data class Direccion(
    val calle: String,
    val numero: Int,
    val puerta: Int?,
    val codigoPostal: Int,
    val ciudad: String
)
