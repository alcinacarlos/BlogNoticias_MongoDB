package org.carlosalcina.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.carlosalcina.DBConnection
import org.carlosalcina.model.Usuario

class UsuarioRepository {

    companion object {
        const val DATABASE_NAME = "blognoticias"
        const val COLLECTION_NAME = "usuarios"
    }

    private fun getCollection(database: MongoDatabase): MongoCollection<Usuario> {
        return database.getCollection(COLLECTION_NAME, Usuario::class.java)
    }

    fun insert(usuario: Usuario): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            coll.insertOne(usuario)
            return true
        } catch (e: Exception) {
            println("Error al insertar: ${e.message}")
            return false
        }
    }

    fun update(oldUsuario: Usuario, updatedUsuario: Usuario): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("_id", oldUsuario.correo)
            val result = coll.replaceOne(filtro, updatedUsuario)
            return result.matchedCount > 0
        } catch (e: Exception) {
            println("Error al actualizar: ${e.message}")
            return false
        }
    }

    fun delete(correo: String): Boolean {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            val filtro = Filters.eq("_id", correo)
            val result = coll.deleteOne(filtro)
            return result.deletedCount > 0
        } catch (e: Exception) {
            println("Error al eliminar: ${e.message}")
            return false
        }
    }

    fun getById(correo: String): Usuario? {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find(Filters.eq("_id", correo)).first()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return null
        }
    }

    fun getByUsername(nombreUsuario: String): Usuario? {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find(Filters.eq("nombreUsuario", nombreUsuario)).first()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return null
        }
    }

    fun getAll(): List<Usuario> {
        try {
            val database = DBConnection.getDatabase(DATABASE_NAME)
            val coll = getCollection(database)
            return coll.find().toList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return emptyList()
        }
    }
}