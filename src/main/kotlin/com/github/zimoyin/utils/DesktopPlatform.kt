package com.github.zimoyin.utils

import androidx.compose.ui.geometry.Size

val iconSize = when (DesktopPlatform.Current) {
    // https://doc.qt.io/qt-5/qtwidgets-desktop-systray-example.html (search 22x22)
    DesktopPlatform.Linux -> Size(22f, 22f)
    // https://doc.qt.io/qt-5/qtwidgets-desktop-systray-example.html (search 16x16)
    DesktopPlatform.Windows -> Size(16f, 16f)
    // https://medium.com/@acwrightdesign/creating-a-macos-menu-bar-application-using-swiftui-54572a5d5f87
    DesktopPlatform.MacOS -> Size(22f, 22f)
    DesktopPlatform.Unknown -> Size(32f, 32f)
}

enum class DesktopPlatform {
    Linux,
    Windows,
    MacOS,
    Unknown;

    companion object {
        /**
         * Identify OS on which the application is currently running.
         */
        val Current: DesktopPlatform by lazy {
            val name = System.getProperty("os.name")
            when {
                name?.startsWith("Linux") == true -> Linux
                name?.startsWith("Win") == true -> Windows
                name == "Mac OS X" -> MacOS
                else -> Unknown
            }
        }
    }
}