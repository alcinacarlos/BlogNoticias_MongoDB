package org.carlosalcina.log

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger(private val logFilePath: String) {

    private val logFile: Path = Paths.get(logFilePath)

    init {
        try {
            if (!Files.exists(logFile)) {
                Files.createFile(logFile)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun log(message: String) {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val logEntry = "[$timestamp] - $message\n"
        try {
            Files.write(logFile, logEntry.toByteArray(), StandardOpenOption.APPEND)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}