package org.carlosalcina.io

import org.carlosalcina.log.Logger
import org.carlosalcina.model.Direccion
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Consola(
    private val logger: Logger
):EntradaSalida {
    override fun mostrar(mensaje: Any, itemLogged: Any?) {
        println(mensaje)
        if (itemLogged != null) {
            logger.log(mensaje.toString())
        }
    }
    override fun pedirInt(enunciado: String): Int {
        var numero: Int? = null
        while (numero == null) {
            println(enunciado)
            numero = readln().toIntOrNull()
        }
        return numero
    }

    override fun pedirString(enunciado: String, maxLength: Int): String {
        var input: String? = null
        while (input == null || input.length > maxLength) {
            println(enunciado)
            input = readln()
            if (input.length > maxLength) {
                println("El texto excede el número máximo de caracteres ($maxLength)")
            }
        }
        return input
    }

    override fun pedirFecha(enunciado: String): LocalDate {
        val dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var fecha: LocalDate? = null
        while (fecha == null) {
            println(enunciado)
            val input = readln()
            try {
                fecha = LocalDate.parse(input, dateTimeFormat)
            } catch (e: Exception) {
                println("Formato inválido, introduce la fecha en formato dd/MM/yyyy")
            }
        }
        return fecha
    }

    override fun pedirDireccion(): Direccion {
        val calle = pedirString("Introduce tu calle: ", 50)

        val numero = pedirInt("Introduce tu número de calle: ")

        println("Quieres especificar la puerta? (s / n)")
        val puerta = if (readln().lowercase() == "s") {
            pedirInt("Introduce tu número de puerta: ")
        } else null

        val cp = pedirInt("Introduce tu número postal: ")

        val ciudad = pedirString("Introduce una ciudad: ", 30)

        return Direccion(calle, numero, puerta, cp, ciudad)
    }

    override fun pedirTelefonoContacto(): List<String> {
        var pedirActvo = true
        val listaTelefonos = mutableListOf<String>()
        while (pedirActvo) {
            println("Introduce un numero de telefono:")
            listaTelefonos.add(readln())
            println("Quieres añadir más telefonos? (s/n)")
            if (readln().lowercase() == "n") {
                pedirActvo = false
            }
        }
        return listaTelefonos
    }

    override fun pedirEtiquetas(): List<String> {
        if (pedirString("Quieres añadir etiquetas a la noticia? (s / n)") == "n") {
            return emptyList()
        }

        var pedirActvo = true
        val listaEtiquetas = mutableListOf<String>()
        while (pedirActvo) {
            println("Introduce una etiqueta:")
            listaEtiquetas.add(readln())
            println("Quieres añadir más etiquetas? (s/n)")
            if (readln().lowercase() == "n") {
                pedirActvo = false
            }
        }
        return listaEtiquetas
    }
}