package exercicis.Ex3_2

import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.util.ArrayList

fun main(Args: Array<String>) {
    val fail = File("src/main/resources/Rutes.dat")
    val serializaableFail = File("src/main/resources/Rutes.obj")
    val reader = DataInputStream(FileInputStream(fail))
    val writer = ObjectOutputStream(FileOutputStream(serializaableFail))

    var nomRuta: String
    var desnivell: Int
    var desnivellAcumulat: Int
    var nomPunt: String
    var latitud: Double
    var longitud: Double

    var numPunts: Int


    while (reader.available() > 0) {
        var llistaDePunts = ArrayList<PuntGeo>()
        nomRuta = reader.readUTF()
        desnivell = reader.readInt()
        desnivellAcumulat = reader.readInt()
        numPunts = reader.readInt()
        for (i in 1..numPunts) {
            nomPunt = reader.readUTF()
            latitud = reader.readDouble()
            longitud = reader.readDouble()
            llistaDePunts.add(PuntGeo(nomPunt, Coordenades(latitud, longitud)))
        }
        var ruta = Ruta(nomRuta, desnivell, desnivellAcumulat, llistaDePunts)
        writer.writeObject(ruta)
    }

}