package com.github.zimoyin.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.onClick
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.github.zimoyin.nav.AbsPage
import com.github.zimoyin.theme.*
import com.github.zimoyin.ui.HorizontalDivider
import com.github.zimoyin.ui.IconButtonByImage
import com.github.zimoyin.ui.Spacer
import java.io.File

/**
 *
 * @author : zimo
 * @date : 2024/01/17
 */
class ScriptPage : AbsPage() {
    val scriptDirPath = "./script"
    override val name: String
        get() = "脚本管理器"

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Page() {
        // 表格形式管理脚本
        // 支持所有脚本，并在设置中配置每种脚本格式的启动参数，包括前缀和参数。如果用户自定义启动参数，就不使用默认的
        // 默认支持脚本 bat/cmd/python/kts/kt/sh
        // 其中 python/kotlin 自动下载环境


        val data = remember { mutableStateListOf<RowData>() }
        LaunchedEffect(Unit) {
            data.addAll(initData())
        }

        LazyColumn(
            modifier = modifier().fillMaxSize(),
        ) {
            item {
                Row(
                    modifier = modifier().fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    //演示
                    Card(
                        RowData(
                            title = "测试脚本",
                            date = "2024-01-17 12:00:00",
                            description = "测试脚本内容",
                            type = "Python",
                            hash = "8848"
                        )
                    )

                    data.forEach {
                        Card(
                            it
                        )
                    }
                }
            }
        }
    }

    private fun initData(): List<RowData> {
        val paths = File(scriptDirPath).let {
            it.mkdirs()
            it.listFiles()?.filter {
                it.isFile && (
                        it.name.endsWith(".bat") ||
                                it.name.endsWith(".cmd") ||
                                it.name.endsWith(".py") ||
                                it.name.endsWith(".kts") ||
                                it.name.endsWith(".kt") ||
                                it.name.endsWith(".sh") ||
                                it.name.endsWith(".info")
                        )
            }
        }
        //TODO

        return listOf()
    }

    data class RowData(
        val title: String,
        val date: String,
        val author: String = "未知",
        val type: String,
        val description: String = "",
        val hash: String,
    )

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Card(
        data: RowData,
        onDelete: () -> Unit = {},
        onEdit: () -> Unit = {},
        onStart: () -> Unit = {},
    ) {
        val bgc = remember { mutableStateOf(Color.White.copy(alpha = 0.4f)) }
        Row(
            modifier = modifier()
                .fillMaxWidth()
                .paddingLeftAndRight(20.dp)
                .paddingTop(10.dp)
                .border(1.dp, Color.White.copy(alpha = 0.2f))
                .background(bgc.value)
                .onHover {
                    onEntered {
                        bgc.value = Color.White.copy(alpha = 0.5f)
                    }
                    onExited {
                        bgc.value = Color.White.copy(alpha = 0.4f)
                    }
                }.onClick {
                    onEdit()
                }
        ) {
            Column(
                modifier = modifier()
                    .padding(5.dp)
            ) {
                Column {
                    Text(data.title, fontSize = TextUnit(18f, TextUnitType.Sp), fontWeight = FontWeight(800))
                    Spacer(4.dp)
                    Text(
                        data.description,
                        fontSize = TextUnit(11f, TextUnitType.Sp),
                        maxLines = 3,
//                        fontStyle = FontStyle.Italic,
                        color = LocalContentColor.current.copy(alpha = 0.8f),
                        fontWeight = FontWeight(600),
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(4.dp)
                HorizontalDivider()
                Spacer(4.dp)
                Row {
                    Text(data.date)
                    Spacer(width = 20.dp)
                    Row(
                        modifier = modifier().fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(data.type)
                        Text(data.author)
                        Row(
                            modifier = modifier().paddingRight(20.dp)
                        ) {
                            IconButtonByImage(Icons.Filled.Send) {
                                onStart()
                            }
                            Spacer(width = 5.dp)
                            IconButtonByImage(Icons.Filled.Delete) {
                                onDelete()
                            }
                        }
                    }
                }
            }
        }
    }
}