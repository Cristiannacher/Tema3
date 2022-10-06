package exercicis.Ex3_2

import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.OutputStream

fun main(Args: Array<String>) {

    val file = File("src/main/resources/Rutes.obj")
    val reader = ObjectInputStream(FileInputStream(file))
    try {
        while (true) {
            val ruta = reader.readObject() as Ruta
            ruta.mostrarRuta()
        }
    } catch (e: EOFException){
        reader.close()
    }

}