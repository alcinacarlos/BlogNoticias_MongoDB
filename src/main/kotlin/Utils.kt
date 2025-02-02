package org.carlosalcina

import org.carlosalcina.model.Direccion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Utils {
    companion object {
        fun pedirDouble(enunciado: String): Double {
            var numero: Double? = null
            while (numero == null) {
                println(enunciado)
                numero = readln().toDoubleOrNull()
            }
            return numero
        }

        fun pedirInt(enunciado: String): Int {
            var numero: Int? = null
            while (numero == null) {
                println(enunciado)
                numero = readln().toIntOrNull()
            }
            return numero
        }

        fun pedirString(enunciado: String, maxLength: Int = 50): String {
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

        fun pedirFecha(enunciado: String): LocalDateTime {
            val dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            var fecha: LocalDateTime? = null
            while (fecha == null) {
                println(enunciado)
                val input = readln()
                try {
                    fecha = LocalDateTime.parse(input, dateTimeFormat)
                } catch (e: Exception) {
                    println("Formato inválido, introduce la fecha en formato dd-MM-yyyy")
                }
            }
            return fecha
        }

        fun pedirDireccion(): Direccion {
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

        fun pedirTelefonoContacto(): List<String> {
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

        fun pedirEtiquetas(): List<String> {
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

        fun formatearFecha(fecha: LocalDateTime): String {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return fecha.format(formatter)
        }
    }
}