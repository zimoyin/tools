import com.github.zimoyin.utils.ex.setCharset
import java.awt.*
import java.awt.event.*
import java.io.FileNotFoundException
import javax.swing.JDialog
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.KeyStroke
import kotlin.system.exitProcess


object Test {
    @Throws(FileNotFoundException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        //使用JDialog 作为JPopupMenu载体
        val popWindow = JDialog().apply {
            isUndecorated = true
            //作为菜单载体不需要存在可以视的窗体
            setSize(0, 0)
        }

//        popWindow.dispose()

        //创建JPopupMenu
        val pop: JPopupMenu = object : JPopupMenu() {
            public override fun firePopupMenuWillBecomeInvisible() {
                popWindow.isVisible = false
                println("JPopupMenu不可见时绑定载体组件popWindow也不可见")
            }
        }.apply {
//            setSize(100, 30)
        }
        //添加菜单选项
        JMenuItem("退出".setCharset("UTF-8")).apply {
            addActionListener { e: ActionEvent? ->
                println("点击了退出选项")
//                exitProcess(0)
            }
//            this.border
//            this.bounds
//            this.background
//            this.foreground
//            this.size
//            this.location
//            this.margin
//            this.graphics
//            this.actionCommand
            val shortcutKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)
            this.accelerator = shortcutKeyStroke
            pop.add(this)
        }

        var menuHeight = 0
        var menuWidth = 0
        for (component in pop.components) {
            if (component is JMenuItem) {
                val size = component.getPreferredSize()
                menuHeight += size.height
                menuWidth += size.width
            }
        }

        println("菜单高度: $menuHeight, 菜单宽度: $menuWidth")

        //创建托盘图标
        val image: Image = Toolkit.getDefaultToolkit().createImage("./sample.png")
        val trayIcon = TrayIcon(image).apply {
            isImageAutoSize = true
            //给托盘图标添加鼠标监听
            addMouseListener(object : MouseAdapter() {
                override fun mouseReleased(e: MouseEvent) {
                    //左键点击
                    when {
                        e.button ==0 -> {
                            println("不知道什么按钮")
                        }
                        e.button == 1 -> {
                            println("按左键了")
                        }
                        e.button == 2 -> {
                            println("?")
                        }
                        e.button == 3 -> {
                            println("右键")
                        }
                        e.button == 3 && e.isPopupTrigger -> {
                            println("x: ${e.x}, y: ${e.y}")
                            val pointer = MouseInfo.getPointerInfo().location
                            val x = pointer.x
                            val y = pointer.y
                            //右键点击弹出JPopupMenu绑定的载体以及JPopupMenu
                            popWindow.setLocation(x, y)
                            popWindow.isVisible = true
                            pop.show(popWindow, 5, 0-(menuHeight+5))
                        }
                        else ->{
                            println("未知按钮")
                        }
                    }
                }
            })

            addActionListener {
                println("不知道是啥")
            }
        }

        // 将托盘图标添加到系统的托盘实例中
        SystemTray.getSystemTray().apply {
            add(trayIcon)
        }
    }
}
