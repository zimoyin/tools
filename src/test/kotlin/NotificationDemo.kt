import java.awt.AWTException
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

class NotificationDemo {
    @Throws(AWTException::class)
    fun displayTray() {
        //Obtain only one instance of the SystemTray object
        val tray = SystemTray.getSystemTray()

        //If the icon is a file
        val image = Toolkit.getDefaultToolkit().createImage("icon.png")

        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
        val trayIcon = TrayIcon(image, "Tray Demo")
        //Let the system resize the image if needed
        trayIcon.isImageAutoSize = true
        //Set tooltip text for the tray icon
        trayIcon.toolTip = "System tray icon demo"
        tray.add(trayIcon)

        trayIcon.displayMessage("Hello, World", "notification demo", TrayIcon.MessageType.INFO)
    }

    companion object {
        @Throws(AWTException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            if (SystemTray.isSupported()) {
                val nd = NotificationDemo()
                nd.displayTray()
            } else {
                System.err.println("System tray not supported!")
            }
        }
    }
}