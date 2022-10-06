package exercicis.Ex3_1

import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

fun main(Args: Array<String>) {
    val fail = File("src/main/resources/Rutes.dat")
    val reader = DataInputStream(FileInputStream(fail))

    var nomRuta: String
    var desnivell: Int
    var desnivellAcumulat: Int
    var nomPunt: String
    var latitud: Double
    var longitud: Double

    var numPunts: Int

    while (reader.available() > 0) {
        nomRuta = reader.readUTF()
        println("Ruta: $nomRuta")
        desnivell = reader.readInt()
        println("Desnivell: $desnivell")
        desnivellAcumulat = reader.readInt()
        println("Desnivell Acumulat: $desnivellAcumulat")
        numPunts = reader.readInt()
        println("Te $numPunts punts" )
        for (i in 1..numPunts) {
            nomPunt = reader.readUTF()
            latitud = reader.readDouble()
            longitud = reader.readDouble()
            println("Punt $i: $nomPunt ($latitud, $longitud)")
        }
        println()
    }

}