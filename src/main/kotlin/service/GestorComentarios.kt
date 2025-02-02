package org.carlosalcina.service

import org.carlosalcina.Utils
import org.carlosalcina.model.Comentario
import org.carlosalcina.model.Noticia
import org.carlosalcina.model.Usuario
import org.carlosalcina.repository.ComentarioRepository
import org.carlosalcina.repository.NoticiaRepository
import java.time.LocalDateTime

class GestorComentarios(
    private val comentarioRepository: ComentarioRepository,
    private val noticiasRepository: NoticiaRepository
) {
    fun escribirComentario(usuario: Usuario) {
        if (!usuario.estado.activo || usuario.estado.baneado) return println("Usuario baneado o inactivo, no puede escribir noticias")

        val noticias = noticiasRepository.getAll()
        if (noticias.isEmpty()) return println("No hay noticias disponibles")

        var noticia:Noticia? = null
        while (noticia == null) {
            println("En qué noticia quieres escribir un comentario?: ")
            noticias.forEachIndexed { index, noticia -> println("$index: $noticia") }
            val numeroNoticia = Utils.pedirInt("Introduce un número de noticia: ")
            try {
                noticia = noticias[numeroNoticia]
            }catch (_:Exception){}
        }

        val texto = Utils.pedirString("Texto del comentario: ", 2000)

        val fecha = LocalDateTime.now()

        val comentario = Comentario(usuario.nombreUsuario, noticia.titulo, texto, fecha)

        if (comentarioRepository.insert(comentario)) {
            println("Comentario insertado con éxito")
        }else{
            println("Error al insertar el comentario")
        }
    }

    fun obtenerComentariosDeNoticia(){
        val noticias = noticiasRepository.getAll()
        if (noticias.isEmpty()) return println("No hay noticias disponibles")

        var noticia:Noticia? = null
        while (noticia == null) {
            println("De qué noticia quieres ver los comentarios: ")
            noticias.forEachIndexed { index, noticia -> println("$index: $noticia") }
            val numeroNoticia = Utils.pedirInt("Introduce un número de noticia: ")
            try {
                noticia = noticias[numeroNoticia]
            }catch (_:Exception){}
        }
        val comentarios = comentarioRepository.getByNoticia(noticia.titulo)
        if (comentarios.isEmpty()){
            return println("No hay comentarios en esa noticia")
        }else{
            comentarios.forEachIndexed { index, comentario -> println("$index: $comentario") }
        }
    }
}