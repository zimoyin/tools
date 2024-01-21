package com.github.zimoyin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.isTraySupported

var isWindowShow: MutableState<Boolean> = mutableStateOf(true)
var isWindowTrayShow: MutableState<Boolean> = mutableStateOf(true)

var WindowState.isShow: Boolean
    get() = isWindowShow.value
    set(value) {
        isWindowShow.value = value
    }

var WindowState.isTrayShow: Boolean
    get() = isWindowTrayShow.value && isTraySupported
    set(value) {
        isWindowTrayShow.value = value && isTraySupported
    }

const val WINDOW_TITLE = "工具箱"
const val WINDOW_ICON_PATH = "sample.png"


val WINDOW_ICON
    @Composable get() = painterResource(WINDOW_ICON_PATH)
