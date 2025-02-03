package org.carlosalcina.io

import org.carlosalcina.model.Direccion
import java.time.LocalDate

interface EntradaSalida {
    fun mostrar(mensaje:Any, itemLogged: Any? = null)
    fun pedirInt(enunciado: String): Int
    fun pedirString(enunciado: String, maxLength: Int = 50): String
    fun pedirFecha(enunciado: String): LocalDate
    fun pedirDireccion(): Direccion
    fun pedirTelefonoContacto(): List<String>
    fun pedirEtiquetas(): List<String>
}