package org.carlosalcina.service

import org.carlosalcina.io.EntradaSalida
import org.carlosalcina.model.Noticia
import org.carlosalcina.model.Usuario
import org.carlosalcina.repository.NoticiaRepository

class NoticiasService(
    private val noticiasRepository: NoticiaRepository,
    private val entradaSalida: EntradaSalida
) {
    fun publicarNoticia(usuario: Usuario) {
        entradaSalida.mostrar("Creando noticia:")
        val titulo = entradaSalida.pedirString("Introduce el título: ", 30)
        val cuerpo = entradaSalida.pedirString("Introduce el cuerpo: ", 1000)
        val fechaPub = entradaSalida.pedirFecha("Introduce la fecha de publicacion: ")
        val autor = usuario.nombreUsuario
        val etiquetas = entradaSalida.pedirEtiquetas()

        val noticia = Noticia(titulo, cuerpo, fechaPub, autor, etiquetas)
        if (noticiasRepository.insert(noticia)){
            entradaSalida.mostrar("Noticia ha sido insertada correctamente", noticia)
        }else{
            entradaSalida.mostrar("Error al insertar la noticia", noticia)
        }
    }
    fun listarNoticiasPorUsuario(){
        val usuarioAMostrar = entradaSalida.pedirString("Introduce el autor del que mostrar la noticias: ")
        val noticias = noticiasRepository.getByAutor(usuarioAMostrar)
        if (noticias.isEmpty()){
            entradaSalida.mostrar("Autor $usuarioAMostrar no tiene noticia publicadas", noticias)
        }else{
            entradaSalida.mostrar("Noticias de $usuarioAMostrar: ", noticias)
            noticias.forEach { entradaSalida.mostrar(it) }
        }
    }
    fun listarNoticiasPorTag(){
        val tag = entradaSalida.pedirString("Introduce la etiqueta de la que mostrar las noticias: ")
        val noticias = noticiasRepository.getByTag(tag)
        if (noticias.isEmpty()){
            entradaSalida.mostrar("No hay noticias con el tag: $tag", tag)
        }else{
            entradaSalida.mostrar("Noticias con el tag: $tag: ", tag)
            noticias.forEach { entradaSalida.mostrar(it) }
        }
    }
    fun listar10UltimasNoticias() {
        val noticias = noticiasRepository.getLast10Published()
        if (noticias.isEmpty()){
            entradaSalida.mostrar("No hay noticias publicadas", noticias)
        }else{
            entradaSalida.mostrar("Las 10 últimas noticias:", noticias)
            noticias.forEach { entradaSalida.mostrar(it) }
        }
    }
}