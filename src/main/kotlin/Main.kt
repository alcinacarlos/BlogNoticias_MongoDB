package org.carlosalcina

import org.carlosalcina.log.Logger
import org.carlosalcina.repository.ComentarioRepository
import org.carlosalcina.repository.NoticiaRepository
import org.carlosalcina.repository.UsuarioRepository
import org.carlosalcina.controller.GestorBlog
import org.carlosalcina.io.Consola
import org.carlosalcina.service.ComentariosService
import org.carlosalcina.service.NoticiasService
import org.carlosalcina.service.UsuariosService

fun main() {
    val logger = Logger("logs.txt")
    val consola = Consola(logger)

    val noticiasRepository = NoticiaRepository()
    val comentarioRepository = ComentarioRepository()
    val usuarioRepository = UsuarioRepository()

    val comentariosService = ComentariosService(comentarioRepository, noticiasRepository, consola)
    val noticiasService = NoticiasService(noticiasRepository, consola)
    val usuariosService = UsuariosService(usuarioRepository, consola)

    val gestorBlog = GestorBlog(noticiasService, usuariosService, comentariosService, consola)

    gestorBlog.menu()

}