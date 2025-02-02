package org.carlosalcina.service

import org.carlosalcina.model.Usuario

class GestorBlog(
    private val gestorNoticias: GestorNoticias,
    private val gestorUsuarios: GestorUsuarios,
    private val gestorComentarios: GestorComentarios
) {
    private var userLogged: Usuario? = null

    fun menu() {
        var menuActivo = true

        while (menuActivo) {

            if (userLogged != null) {
                showLoggedMenu()
                when (readln().toIntOrNull()) {
                    0 -> menuActivo = false
                    1 -> {
                        userLogged = null
                        println("Sesión cerrado con éxito")
                    }

                    2 -> gestorNoticias.publicarNoticia(userLogged!!)
                    3 -> gestorComentarios.escribirComentario(userLogged!!)
                    4 -> gestorNoticias.listarNoticiasPorUsuario()
                    5 -> gestorComentarios.obtenerComentariosDeNoticia()
                    6 -> gestorNoticias.listarNoticiasPorTag()
                    7 -> gestorNoticias.listar10UltimasNoticias()
                    else -> println("Opcion no válida")
                }
            } else {
                showMenu()
                when (readln().toIntOrNull()) {
                    0 -> menuActivo = false
                    1 -> gestorUsuarios.registrarUsuario()
                    2 -> userLogged = gestorUsuarios.iniciarSesionFake()
                    3 -> gestorNoticias.listarNoticiasPorUsuario()
                    4 -> gestorComentarios.obtenerComentariosDeNoticia()
                    5 -> gestorNoticias.listarNoticiasPorTag()
                    6 -> gestorNoticias.listar10UltimasNoticias()
                    else -> println("Opcion no válida")
                }
            }

        }
    }

    private fun showLoggedMenu() {
        println("=== Menú de Noticias ===")
        println("0. Salir")
        println("1. Cerrar sesión")
        println("2. Publicar noticia")
        println("3. Escribir comentario en noticia")
        println("4. Listar noticias publicadas por un usuario")
        println("5. Listar comentarios de una noticia")
        println("6. Buscar noticias por etiquetas")
        println("7. Listar las 10 últimas noticias publicadas")

    }

    private fun showMenu() {
        println("=== Menú de Noticias ===")
        println("0. Salir")
        println("1. Registrar usuario")
        println("2. Iniciar sesión")
        println("3. Listar noticias publicadas por un usuario")
        println("4. Listar comentarios de una noticia")
        println("5. Buscar noticias por etiquetas")
        println("6. Listar las 10 últimas noticias publicadas")
    }
}