package org.carlosalcina.service

import org.carlosalcina.Utils
import org.carlosalcina.model.Noticia
import org.carlosalcina.model.Usuario
import org.carlosalcina.repository.NoticiaRepository

class GestorNoticias(
    private val noticiasRepository: NoticiaRepository
) {
    fun publicarNoticia(usuario: Usuario) {
        println("Creando noticia:")
        val titulo = Utils.pedirString("Introduce el título: ", 30)
        val cuerpo = Utils.pedirString("Introduce el cuerpo: ", 1000)
        val fechaPub = Utils.pedirFecha("Introduce la fecha de publicacion: ")
        val autor = usuario.nombreUsuario
        val etiquetas = Utils.pedirEtiquetas()

        val noticias = Noticia(titulo, cuerpo, fechaPub, autor, etiquetas)
        if (noticiasRepository.insert(noticias)){
            println("Noticia ha sido insertada correctamente")
        }else{
            println("Error al insertar la noticia")
        }
    }
    fun listarNoticiasPorUsuario(){
        val usuarioAMostrar = Utils.pedirString("Introduce el autor del que mostrar la noticias: ")
        val noticias = noticiasRepository.getByAutor(usuarioAMostrar)
        if (noticias.isEmpty()){
            println("Autor no tiene noticia publicadas")
        }else{
            noticias.forEach { println(it) }
        }
    }
    fun listarNoticiasPorTag(){
        val tag = Utils.pedirString("Introduce la etiqueta de la que mostrar las noticias: ")
        val noticias = noticiasRepository.getByTag(tag)
        if (noticias.isEmpty()){
            println("No hay noticias con ese tag")
        }else{
            noticias.forEach { println(it) }
        }
    }
    fun listar10UltimasNoticias() {
        val noticias = noticiasRepository.getLast10Published()
        if (noticias.isEmpty()){
            println("No hay noticias")
        }else{
            noticias.forEach { println(it) }
        }
    }
}