package com.github.zimoyin

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.github.zimoyin.theme.modifier
import com.github.zimoyin.ui.tray.ButtonType
import com.github.zimoyin.ui.tray.TrayWindow
import com.github.zimoyin.utils.onWindowSize
import javax.swing.UIManager


lateinit var WindowState: WindowState
    private set

lateinit var trayState: TrayState
    private set

/**
 * 注意启动需要设置 -Dfile.encoding=gbk 否则在托盘的字体会乱码
 */
fun main() = application {
    val state = rememberWindowState()
    DisposableEffect(Unit) {
        state.size = DpSize(1050.dp, 672.dp)

        onDispose {

        }
    }
    if (state.size.height.value % 2.0 == 0.0) {
        state.size = DpSize(state.size.width, state.size.height + 1.dp)
    }
    WindowState = state

    if (state.isTrayShow) {
        OpenTray()
    }

    if (state.isShow) {
        OpenWindow(state)
    }

    onWindowSize {
        println("窗体大小: $it")
    }

}

@Composable
fun ApplicationScope.OpenWindow(state: WindowState) {
    Window(
        onCloseRequest = {
            isWindowShow.value = false
        },
        icon = WINDOW_ICON,
        title = WINDOW_TITLE,
        state = state,
        visible = true,
        resizable = true,
    ) {
        MaterialTheme(
//            colors = if (isSystemInDarkTheme()) darkColors() else lightColors(),
            colors = darkColors(),
        ) {
            Surface(
                modifier = modifier().fillMaxSize()
            ) {
                Row(
                    modifier = modifier()
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF00FFFF).copy(alpha = 0.7f),
                                    Color(0xFF00AAFF).copy(alpha = 0.8f),
                                    Color(0xFF0077FF).copy(),
                                ),
                                start = Offset(WindowState.size.width.value, 0f),
                                end = Offset(0f, WindowState.size.height.value + 300f),
                            )
                        )
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun ApplicationScope.OpenTray() {
    trayState = rememberTrayState()

    val showExitButton = remember { mutableStateOf(true) }

    TrayWindow(
        icon = WINDOW_ICON,
        tooltip = WINDOW_TITLE,
        state = trayState,
        isSort = true,
        setLookAndFeel = UIManager.getSystemLookAndFeelClassName(),
        onAction = {
            if (!isWindowShow.value) isWindowShow.value = true
            WindowState.isMinimized = false
        },
        onClick = {
            if (it.buttonType == ButtonType.LEFT) {
                if (!isWindowShow.value) showExitButton.value = true
                if (WindowState.isMinimized) WindowState.isMinimized = false
            }
        }
    ) {
        Item("打开窗体") {
            isWindowShow.value = true
        }
        Item(
            "退出",
            onClick = ::exitApplication
        )
    }
}

@Composable
fun WindowState.CloseWindow() {
    isWindowShow.value = false
}

@Composable
fun WindowState.Exit() {
    isShow = false
    isTrayShow = false
}


