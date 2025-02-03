package org.carlosalcina.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.carlosalcina.DBConnection
import org.carlosalcina.model.Comentario

class ComentarioRepository {
    companion object {
        const val DATABASE_NAME = "blognoticias"
        const val COLLECTION_NAME = "comentarios"
    }

    private fun getCollection(database: MongoDatabase): MongoCollection<Comentario> {
        return database.getCollection(COLLECTION_NAME, Comentario::class.java)
    }

    fun insert(comentario: Comentario): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            coll.insertOne(comentario)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun getByNoticia(noticiaId: String): List<Comentario> {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("noticiaId", noticiaId)
            return coll.find(filtro).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}
