import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.*
import javax.swing.plaf.PopupMenuUI

fun main() {
    SwingUtilities.invokeLater {

//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

        println(UIManager.getSystemLookAndFeelClassName())

        val frame = JFrame("PopupMenuUI Example")
        val popupMenu = JPopupMenu()
//        popupMenu.isBorderPainted = false
//        popupMenu.isOpaque = false
        popupMenu.background = Color(0, 0, 0, 0)
        popupMenu.border = BorderFactory.createLineBorder(Color(0, 0, 0, 0x40))
        popupMenu.foreground = Color(0, 0, 0, 0)
        popupMenu.label = null


        val item1 = JMenuItem("Option 1")
        val item2 = JMenuItem("Option 2")
        val item3 = JMenuItem("Option 3")



        popupMenu.add(item1)
        popupMenu.add(item2)
        popupMenu.add(item3)


        val showPopupButton = JButton("Show Popup")
        showPopupButton.addActionListener {
            popupMenu.show(showPopupButton, 0, showPopupButton.height)
        }
        showPopupButton.isFocusable = false

        val panel = JPanel().apply {
            add(showPopupButton)
        }

        frame.contentPane.add(panel)
        frame.size = Dimension(300, 200)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
    }
}
