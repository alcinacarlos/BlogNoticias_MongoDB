package org.carlosalcina.controller

import org.carlosalcina.io.EntradaSalida
import org.carlosalcina.model.Usuario
import org.carlosalcina.service.ComentariosService
import org.carlosalcina.service.NoticiasService
import org.carlosalcina.service.UsuariosService

class GestorBlog(
    private val noticiasService: NoticiasService,
    private val usuariosService: UsuariosService,
    private val comentariosService: ComentariosService,
    private val entradaSalida: EntradaSalida
) {
    private var userLogged: Usuario? = null

    fun menu() {
        var menuActivo = true

        while (menuActivo) {

            if (userLogged != null) {
                showLoggedMenu()
                
                val input = entradaSalida.pedirInt("Elige una opción: ")
                when (input) {
                    0 -> menuActivo = false
                    1 -> {
                        userLogged = null
                        entradaSalida.mostrar("Sesión cerrado con éxito")
                    }

                    2 -> noticiasService.publicarNoticia(userLogged!!)
                    3 -> comentariosService.escribirComentario(userLogged!!)
                    4 -> noticiasService.listarNoticiasPorUsuario()
                    5 -> comentariosService.obtenerComentariosDeNoticia()
                    6 -> noticiasService.listarNoticiasPorTag()
                    7 -> noticiasService.listar10UltimasNoticias()
                    else -> entradaSalida.mostrar("Opcion no válida")
                }
            } else {
                showMenu()
                val input = entradaSalida.pedirInt("Elige una opción: ")
                when (input) {
                    0 -> menuActivo = false
                    1 -> usuariosService.registrarUsuario()
                    2 -> userLogged = usuariosService.iniciarSesionFake()
                    3 -> noticiasService.listarNoticiasPorUsuario()
                    4 -> comentariosService.obtenerComentariosDeNoticia()
                    5 -> noticiasService.listarNoticiasPorTag()
                    6 -> noticiasService.listar10UltimasNoticias()
                    else -> entradaSalida.mostrar("Opcion no válida")
                }
            }

        }
    }

    private fun showLoggedMenu() {
        entradaSalida.mostrar("\nMenú de Noticias")
        entradaSalida.mostrar("\t0. Salir")
        entradaSalida.mostrar("\t1. Cerrar sesión")
        entradaSalida.mostrar("\t2. Publicar noticia")
        entradaSalida.mostrar("\t3. Escribir comentario en noticia")
        entradaSalida.mostrar("\t4. Listar noticias publicadas por un usuario")
        entradaSalida.mostrar("\t5. Listar comentarios de una noticia")
        entradaSalida.mostrar("\t6. Buscar noticias por etiquetas")
        entradaSalida.mostrar("\t7. Listar las 10 últimas noticias publicadas")

    }

    private fun showMenu() {
        entradaSalida.mostrar("\nMenú de Noticias")
        entradaSalida.mostrar("\t0. Salir")
        entradaSalida.mostrar("\t1. Registrar usuario")
        entradaSalida.mostrar("\t2. Iniciar sesión")
        entradaSalida.mostrar("\t3. Listar noticias publicadas por un usuario")
        entradaSalida.mostrar("\t4. Listar comentarios de una noticia")
        entradaSalida.mostrar("\t5. Buscar noticias por etiquetas")
        entradaSalida.mostrar("\t6. Listar las 10 últimas noticias publicadas")
    }
}