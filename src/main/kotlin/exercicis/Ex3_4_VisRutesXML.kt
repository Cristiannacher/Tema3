package exercicis

import javax.swing.*
import java.awt.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory


class Finestra : JFrame() {

    init {
        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("src/main/resources/Rutes.xml")
        // sentències per a omplir doc

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1,BorderLayout.NORTH)
        add(panell2,BorderLayout.CENTER)

        val llistaRutes = arrayListOf<String>()
        // sentències per a omplir l'ArrayList anterior amb el nom de les rutes
        val arrel = doc.getDocumentElement()
        val listaDeNodos = arrel.getElementsByTagName("ruta")
        for(i in 0 until listaDeNodos.length){
            llistaRutes.add(listaDeNodos.item(i).firstChild.textContent)
        }


        val combo = JComboBox(llistaRutes.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"),BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener{
            // accions quan s'ha seleccionat un element del combobox,
            // i que han de consistir en omplir el JTextArea
            val index = combo.selectedIndex
            val ruta = listaDeNodos.item(index) as Element
            val punts = ruta.getElementsByTagName("punt")
            var info = ""
            for (i in 0..punts.length){
                val infoPunts = punts.item(i).childNodes
                for (e in 0..infoPunts.length){
                    info += infoPunts.item(i).textContent +" "
                }
                area.text += info
            }

        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}