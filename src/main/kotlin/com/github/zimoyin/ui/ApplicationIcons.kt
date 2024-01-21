package com.github.zimoyin.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.github.zimoyin.theme.modifier
import com.github.zimoyin.theme.paddingRight

/**
 *
 * @author : zimo
 * @date : 2024/01/17
 */
@OptIn(ExperimentalComposeUiApi::class)
object ApplicationIcons : HashMap<String, Painter>() {
    init {
        Icons.Filled.Lock
    }

    @Composable
    fun getIconCompose(
        key: String,
        modifier: Modifier = modifier().paddingRight(4.dp).size(20.dp),
    ) {
        get(key)?.let {
            Icon(it, key, modifier)
        }
    }
}