package org.carlosalcina.service

import org.carlosalcina.io.EntradaSalida
import org.carlosalcina.model.EstadoUsuario
import org.carlosalcina.model.Usuario
import org.carlosalcina.repository.UsuarioRepository

class UsuariosService(
    private val usuarioRepository: UsuarioRepository,
    private val entradaSalida: EntradaSalida
) {
    fun registrarUsuario() {
        entradaSalida.mostrar("Registrando usuario")

        val correo = pedirCorreo()

        val nombreCompleto = entradaSalida.pedirString("Nombre Completo: ", 30)

        val nombreUsuario = pedirUsername()

        val estadoUsuario = EstadoUsuario(
            baneado = false,
            activo = true
        )

        val direccion = entradaSalida.pedirDireccion()

        val telefonosContacto = entradaSalida.pedirTelefonoContacto()

        val usuario = Usuario(correo, nombreCompleto, nombreUsuario, estadoUsuario, direccion, telefonosContacto)

        if (usuarioRepository.insert(usuario)) {
            entradaSalida.mostrar("Usuario registrado correctamente", usuario)
        } else {
            entradaSalida.mostrar("Error al registrar usuario", usuario)
        }

    }

    fun iniciarSesionFake():Usuario? {
        val correo = entradaSalida.pedirString("Introduce tu correo (no hace falta contraseña): ")
        val usuario = usuarioRepository.getById(correo)
        if (usuario == null){
            entradaSalida.mostrar("Correo no registrado", correo)
        }else{
            entradaSalida.mostrar("Sesion iniciado correctamente", usuario)
        }
        return usuario
    }


    private fun pedirCorreo(): String {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex() // Expresión regular para validar el formato del correo
        var pedirActivo = true
        var correo = ""

        while (pedirActivo) {
            correo = entradaSalida.pedirString("Introduce un correo: ")

            if (!correo.matches(emailRegex)) {
                entradaSalida.mostrar("Correo inválido, introduce un correo válido", correo)
                continue
            }

            if (usuarioRepository.getById(correo) == null) {
                pedirActivo = false
            } else {
                entradaSalida.mostrar("Correo ya registrado, elige otro", correo)
            }
        }
        return correo
    }

    private fun pedirUsername(): String {
        var pedirActvo = true
        var username = ""
        while (pedirActvo) {
            username = entradaSalida.pedirString("Introduce un nombre de usuario: ")
            if (usuarioRepository.getByUsername(username) == null) {
                pedirActvo = false
            } else {
                entradaSalida.mostrar("Nombre de usuario ya registrado, elige otro", username)
            }
        }
        return username
    }


}