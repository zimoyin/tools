package com.github.zimoyin

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.github.zimoyin.nav.AbsPage
import com.github.zimoyin.nav.Nav
import com.github.zimoyin.pages.HomePage
import com.github.zimoyin.pages.ScriptPage
import com.github.zimoyin.theme.modifier
import com.github.zimoyin.theme.onHover
import com.github.zimoyin.theme.paddingLeftAndRight
import com.github.zimoyin.theme.paddingTopAndBottom
import com.github.zimoyin.ui.VerticalDivider
import com.github.zimoyin.utils.Color

val BUTTON_MAP = LinkedHashMap<Pair<String, @Composable () -> Unit>, AbsPage>()


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    SimulateInitialization()
    if (BUTTON_MAP.isEmpty()) {
        Column(
            modifier = modifier().fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                "无任何组件",
                modifier = modifier().align(alignment = Alignment.CenterHorizontally),
                fontSize = TextUnit(6f, TextUnitType.Em)
            )
        }
        return
    }
    var currentPageName by remember { mutableStateOf(BUTTON_MAP.firstEntry().value.name) }
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier()
                .widthIn(150.dp, 180.dp),
        ) {
            BUTTON_MAP.forEach { (key, _) ->
                var backgroundColor by remember { mutableStateOf(Color.Transparent) }
                Row(
                    modifier()
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .onHover {
                            onExited {
                                backgroundColor = Color.Transparent
                            }
                            onEntered {
                                backgroundColor = Color("#FFFFFF30")
                            }
                        }
                        .onClick {
                            currentPageName = key.first
                        }
                        .paddingLeftAndRight(20.dp)
                        .paddingTopAndBottom(7.dp)
                ) {
                    Row(
                        modifier = modifier().fillMaxWidth().padding(10.dp),
                    ) {
                        key.second.invoke()
                    }
                }
            }
        }

        VerticalDivider()
        Column {
            Nav {
                BUTTON_MAP.forEach { (_, value) ->
                    add(value)
                }

                goto(currentPageName)
            }
        }
    }
}

@Composable
fun SimulateInitialization() {
    BUTTON_MAP.clear()
    HomePage().apply {
        BUTTON_MAP.add(this.name, page = this)
    }
    ScriptPage().apply {
        BUTTON_MAP.add(this.name, page = this)
    }
}
