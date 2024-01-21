package com.github.zimoyin

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.github.zimoyin.nav.AbsPage
import com.github.zimoyin.theme.modifier
import com.github.zimoyin.ui.ApplicationIcons


@Composable
fun LinkedHashMap<Pair<String, @Composable () -> Unit>, AbsPage>.add(
    text: String,
    modifier: Modifier = modifier(),
    nav: (@Composable () -> Unit)? = null,
    page: AbsPage,
) {
    val pair = Pair<String, @Composable () -> Unit>(text) @Composable {
        if (nav == null) {
            ApplicationIcons.getIconCompose(text)
            Text(text, modifier = modifier, fontSize = TextUnit(14.5f, TextUnitType.Sp))
        } else nav()
    }
    this[pair] = page
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LinkedHashMap<Pair<String, @Composable () -> Unit>, AbsPage>.add(
    text: String,
    modifier: Modifier = modifier(),
    nav: (@Composable () -> Unit)? = null,
    content: @Composable AbsPage.() -> Unit = {},
) {
    val pair = Pair<String, @Composable () -> Unit>(text) @Composable {
        if (nav == null) {
            ApplicationIcons.getIconCompose(text)
            Text(text, modifier = modifier)
        } else nav()
    }
    this[pair] = object : AbsPage() {
        override val name: String
            get() = text

        @Composable
        override fun Page() {
            content()
        }
    }
}


fun HashMap<String, AbsPage>.add(page: AbsPage){
    this[page.name] = page
}

