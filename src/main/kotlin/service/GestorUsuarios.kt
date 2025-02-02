package org.carlosalcina.service

import org.carlosalcina.Utils
import org.carlosalcina.model.EstadoUsuario
import org.carlosalcina.model.Usuario
import org.carlosalcina.repository.UsuarioRepository

class GestorUsuarios(
    private val usuarioRepository: UsuarioRepository
) {
    fun registrarUsuario() {
        println("Registrando usuario")

        val correo = pedirCorreo()

        val nombreCompleto = Utils.pedirString("Nombre Completo: ", 30)

        val nombreUsuario = pedirUsername()

        val estadoUsuario = EstadoUsuario(
            baneado = false,
            activo = true
        )

        val direccion = Utils.pedirDireccion()

        val telefonosContacto = Utils.pedirTelefonoContacto()

        val usuario = Usuario(correo, nombreCompleto, nombreUsuario, estadoUsuario, direccion, telefonosContacto)

        if (usuarioRepository.insert(usuario)) {
            println("Usuario registrado correctamente")
        } else {
            println("Error al registrar usuario")
        }

    }

    fun iniciarSesionFake(): Boolean {
        val correo = Utils.pedirString("Introduce tu correo (no hace falta contraseña): ")
        return usuarioRepository.getById(correo) != null
    }


    private fun pedirCorreo(): String {
        var pedirActvo = true
        var correo = ""
        while (pedirActvo) {
            println("Introduce un correo:")
            correo = readln()
            if (usuarioRepository.getById(correo) == null) {
                pedirActvo = false
            } else {
                println("Correo ya registrado, elige otro")
            }
        }
        return correo
    }

    private fun pedirUsername(): String {
        var pedirActvo = true
        var username = ""
        while (pedirActvo) {
            println("Introduce un nombre de usuario:")
            username = readln()
            if (usuarioRepository.getByUsername(username) == null) {
                pedirActvo = false
            } else {
                println("Nombre de usuario ya registrado, elige otro")
            }
        }
        return username
    }


}