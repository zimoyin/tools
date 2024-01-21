package com.github.zimoyin.utils

import androidx.compose.ui.graphics.Color

fun Color(hexColor: String): Color {
    val color = hexColor.replace("#", "") // 去掉 "#" 符号
    return when (color.length) {
        6 -> {
            Color(
                color.substring(0, 2).toInt(16),
                color.substring(2, 4).toInt(16),
                color.substring(4, 6).toInt(16)
            )
        }

        8 -> {
            Color(
                color.substring(0, 2).toInt(16),
                color.substring(2, 4).toInt(16),
                color.substring(4, 6).toInt(16),
                color.substring(6, 8).toInt(16)
            )
        }

        else -> throw IllegalArgumentException("Invalid HEX color format. It should be in the format #RGB or #ARGB")
    }
}