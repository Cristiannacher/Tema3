package exercicis

import javax.swing.*
import java.awt.*
import com.squareup.moshi.Moshi
import exercicis.Ex3_2.Ruta
import exercicis.Ex3_5.Rutes
import java.io.File

class FinestraJSON : JFrame() {

    init {
        var llistaRutes: ArrayList<Ruta>
        val json = File("src/main/resources/Rutes.json").readText()
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Rutes::class.java)
        val rutes = adapter.fromJson(json)
        llistaRutes = rutes!!.rutes as ArrayList<Ruta>

        setTitle("JSON: Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        var nomsLlistaRutes = arrayListOf<String>()
        for (i in llistaRutes) {
            nomsLlistaRutes.add(i.nom)
        }
        // sentències per a omplir l'ArrayList anterior amb el nom de les rutes

        val combo = JComboBox(nomsLlistaRutes.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"), BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener {
            // accions quan s'ha seleccionat un element del combobox,
            // i que han de consistir en omplir el JTextArea
            area.text = ""
            val index = combo.selectedIndex
            val ruta = llistaRutes.get(index)
            val punts = ruta.llistaDePunts
//            for (i in 0..ruta.size()-1){
//                area.append(ruta.getPuntNom(i)+" ("+ruta.getPuntLongitud(i)+","+ruta.getPuntLatitud(i)+")\n")
//            } altra opcio
            for (i in punts){
                area.append (i.nom+" ("+i.coord.longitud+","+i.coord.latitud+")\n")
            }

        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        FinestraJSON().isVisible = true
    }
}