package com.github.zimoyin.utils.ex

import androidx.compose.ui.graphics.Color


fun androidx.compose.ui.graphics.Color.toAwtColor() = java.awt.Color(this.red, this.green, this.blue, this.alpha)