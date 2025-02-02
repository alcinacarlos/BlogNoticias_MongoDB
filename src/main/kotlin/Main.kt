package org.carlosalcina

import org.carlosalcina.repository.ComentarioRepository
import org.carlosalcina.repository.NoticiaRepository
import org.carlosalcina.repository.UsuarioRepository
import org.carlosalcina.service.GestorBlog
import org.carlosalcina.service.GestorComentarios
import org.carlosalcina.service.GestorNoticias
import org.carlosalcina.service.GestorUsuarios

fun main() {
    val noticiasRepository = NoticiaRepository()
    val comentarioRepository = ComentarioRepository()
    val usuarioRepository = UsuarioRepository()

    val gestorComentarios = GestorComentarios(comentarioRepository, noticiasRepository)
    val gestorNoticias = GestorNoticias(noticiasRepository)
    val gestorUsuarios = GestorUsuarios(usuarioRepository)

    val gestorBlog = GestorBlog(gestorNoticias, gestorUsuarios, gestorComentarios)

    gestorBlog.menu()

}