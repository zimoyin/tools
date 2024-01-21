package com.github.zimoyin.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import com.github.zimoyin.WindowState
import com.github.zimoyin.isShow
import com.github.zimoyin.isTrayShow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


/**
 * 监听窗口大小变化
 */
@Composable
fun onWindowSize(content: (DpSize) -> Unit) {
    LaunchedEffect(WindowState) {
        snapshotFlow { WindowState.size }
            .onEach {
                content(it)
            }
            .launchIn(this)
    }
}

/**
 * 监听窗口大小变化
 */
@Composable
fun onWindowRelocate(content: (WindowPosition) -> Unit) {
    LaunchedEffect(WindowState) {
        snapshotFlow { WindowState.position }
            .onEach {
                content(it)
            }
            .launchIn(this)
    }
}

/**
 * 监听窗口最小化或者原始状态
 */
@Composable
fun onWindowMinimize(content: (Boolean) -> Unit) {
    LaunchedEffect(WindowState) {
        snapshotFlow { WindowState.isMinimized }
            .onEach {
                content(it)
            }
            .launchIn(this)
    }
}

/**
 * 监听窗口变化： 最小化，全屏等
 */
@Composable
fun onWindowPlacement(content: (WindowPlacement) -> Unit) {
    LaunchedEffect(WindowState) {
        snapshotFlow { WindowState.placement }
            .onEach {
                content(it)
            }
            .launchIn(this)
    }
}

/**
 * 监听主窗口是否显示
 */
@Composable
fun onWindowShow(content: (Boolean) -> Unit) {
    LaunchedEffect(WindowState) {
        snapshotFlow { WindowState.isShow }
            .onEach {
                content(it)
            }
            .launchIn(this)
    }
}

/**
 * 监听托盘是否显示
 */
@Composable
fun onWindowTrayShow(content: (Boolean) -> Unit) {
    LaunchedEffect(WindowState) {
        snapshotFlow { WindowState.isTrayShow }
            .onEach {
                content(it)
            }
            .launchIn(this)
    }
}