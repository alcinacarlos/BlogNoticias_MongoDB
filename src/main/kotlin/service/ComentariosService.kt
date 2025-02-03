package org.carlosalcina.service

import org.carlosalcina.io.EntradaSalida
import org.carlosalcina.model.Comentario
import org.carlosalcina.model.Noticia
import org.carlosalcina.model.Usuario
import org.carlosalcina.repository.ComentarioRepository
import org.carlosalcina.repository.NoticiaRepository
import java.time.LocalDate

class ComentariosService(
    private val comentarioRepository: ComentarioRepository,
    private val noticiasRepository: NoticiaRepository,
    private val entradaSalida: EntradaSalida
) {
    fun escribirComentario(usuario: Usuario) {
        if (!usuario.estado.activo || usuario.estado.baneado){
            return entradaSalida.mostrar("Usuario baneado o inactivo, no puede escribir noticias", usuario)
        }

        val noticias = noticiasRepository.getAll()
        if (noticias.isEmpty()){
            return entradaSalida.mostrar("No hay noticias disponibles para comentar", noticias)
        }

        var noticia:Noticia? = null
        while (noticia == null) {
            entradaSalida.mostrar("En qué noticia quieres escribir un comentario?: ")
            noticias.forEachIndexed { index, noticia -> entradaSalida.mostrar("$index: $noticia") }
            val numeroNoticia = entradaSalida.pedirInt("Introduce un número de noticia: ")
            try {
                noticia = noticias[numeroNoticia]
            }catch (_:Exception){}
        }

        val texto = entradaSalida.pedirString("Texto del comentario: ", 2000)

        val fecha = LocalDate.now()

        val comentario = Comentario(usuario.nombreUsuario, noticia.titulo, texto, fecha)

        if (comentarioRepository.insert(comentario)) {
            entradaSalida.mostrar("Comentario insertado con éxito", comentario)
        }else{
            entradaSalida.mostrar("Error al insertar el comentario", comentario)
        }
    }

    fun obtenerComentariosDeNoticia(){
        val noticias = noticiasRepository.getAll()
        if (noticias.isEmpty()){
            return entradaSalida.mostrar("No hay noticias disponibles", noticias)
        }

        var noticia:Noticia? = null
        while (noticia == null) {
            entradaSalida.mostrar("De qué noticia quieres ver los comentarios: ")
            noticias.forEachIndexed { index, noticia -> entradaSalida.mostrar("$index: $noticia") }
            val numeroNoticia = entradaSalida.pedirInt("Introduce un número de noticia: ")
            try {
                noticia = noticias[numeroNoticia]
            }catch (_:Exception){}
        }
        val comentarios = comentarioRepository.getByNoticia(noticia.titulo)
        if (comentarios.isEmpty()){
            return entradaSalida.mostrar("No hay comentarios en esa noticia", noticia)
        }else{
            entradaSalida.mostrar("Mostrando comentarios: ", noticias)
            comentarios.forEachIndexed { index, comentario -> entradaSalida.mostrar("$index: $comentario") }
        }
    }
}