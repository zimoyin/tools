package com.github.zimoyin.pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.zimoyin.nav.AbsPage

/**
 *
 * @author : zimo
 * @date : 2024/01/17
 */
class HomePage : AbsPage() {
    override val name: String
        get() = "主页"

    @Composable
    override fun Page() {
        Text("主页")
    }
}