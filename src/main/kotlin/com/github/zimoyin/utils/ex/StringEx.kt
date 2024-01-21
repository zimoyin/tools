package com.github.zimoyin.utils.ex

import org.intellij.lang.annotations.Language
import java.nio.charset.Charset

fun String.setCharset(@Language("Charset") charsetName: String): String {
    return String(this.toByteArray(Charset.forName(charsetName)), Charset.forName(charsetName))
}

fun String.setCharsetGBK(): String {
    return String(this.toByteArray(Charset.forName("GBK")), Charset.forName("GBK"))
}