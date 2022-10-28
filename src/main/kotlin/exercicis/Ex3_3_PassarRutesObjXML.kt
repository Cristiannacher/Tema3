package exemples

import exercicis.Ex3_2.Ruta
import java.io.ObjectInputStream
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory
import java.io.EOFException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>) {
    val f = ObjectInputStream(FileInputStream("src/main/resources/Rutes.obj"))

    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
    val arrel = doc.createElement("rutes")
    doc.appendChild(arrel)

    try {
        while (true) {
            val e = f.readObject() as Ruta
            val ruta = doc.createElement("ruta")

            val nomRuta = doc.createElement("nom")
            nomRuta.setTextContent(e.nom)
            ruta.appendChild(nomRuta)

            val des = doc.createElement("desnivell")
            des.setTextContent(e.desnivell.toString())
            ruta.appendChild(des)

            val desAcum = doc.createElement("desnivellAcumulat")
            desAcum.setTextContent(e.desnivellAcumulat.toString())
            ruta.appendChild(desAcum)

            val punts = doc.createElement("punts")
            ruta.appendChild(punts)

            for (i in 1..e.llistaDePunts.size - 1) {
                val punt = doc.createElement("punt")
                punt.setAttribute("num", Integer.toString(i))
                val nomPunt = doc.createElement("nom")
                nomPunt.textContent = e.getPuntNom(i)
                punt.appendChild(nomPunt)
                val latitud = doc.createElement("latitud")
                latitud.textContent = e.getPuntLatitud(i).toString()
                punt.appendChild(latitud)
                val longitud = doc.createElement("longitud")
                longitud.textContent = e.getPuntLongitud(i).toString()
                punt.appendChild(longitud)

                punts.appendChild(punt)
            }
            arrel.appendChild(ruta)
        }

    } catch (eof: EOFException) {
        f.close();
    }
    val trans = TransformerFactory.newInstance().newTransformer()

    trans.transform(DOMSource(doc), StreamResult("src/main/resources/Rutes.xml"))
}