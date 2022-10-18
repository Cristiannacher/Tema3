package exercicis.Ex3_5

import com.squareup.moshi.Moshi
import exercicis.Ex3_2.Ruta
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

fun main(Args: Array<String>) {
    val file = File("src/main/resources/Rutes.obj")
    val reader = ObjectInputStream(FileInputStream(file))
    val rutes = Rutes()
    try {
        while (true) {
            rutes.rutes.add(reader.readObject() as Ruta)
        }
    } catch (e: EOFException) {
        reader.close()
    }
    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(Rutes::class.java)
    val json = adapter.toJson(rutes)

    File("src/main/resources/Rutes.json").writeText(json)
}
