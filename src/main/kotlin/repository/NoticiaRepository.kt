package org.carlosalcina.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import org.carlosalcina.DBConnection
import org.carlosalcina.model.Noticia

class NoticiaRepository {

    companion object {
        const val DATABASE_NAME = "blognoticias"
        const val COLLECTION_NAME = "noticias"
    }

    private fun getCollection(database: MongoDatabase): MongoCollection<Noticia> {
        return database.getCollection(COLLECTION_NAME, Noticia::class.java)
    }

    fun insert(noticia: Noticia): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            coll.insertOne(noticia)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun update(oldNoticia: Noticia, updatedNoticia: Noticia): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("_id", oldNoticia.titulo)
            val result = coll.replaceOne(filtro, updatedNoticia)
            return result.matchedCount > 0
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun delete(id: String): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("_id", id)
            val result = coll.deleteOne(filtro)
            return result.deletedCount > 0
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun get(id: String): Noticia? {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find(Filters.eq("_id", id)).first()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    fun getByAutor(autor: String): List<Noticia> {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find(Filters.eq("autor", autor)).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
    fun getByTag(etiqueta: String): List<Noticia> {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find(Filters.`in`("etiquetas", etiqueta)).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun getLast10Published(): List<Noticia> {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val sortDescending = Sorts.descending("fechaPublicacion")
            return coll.find()
                .sort(sortDescending)
                .limit(10)
                .toList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun getAll(): List<Noticia> {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find().toList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}