package com.github.zimoyin.ui.table

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Table(
    content: @Composable TableScope.() -> Unit,
): TableScope {
    val scope = remember { TableScope() }
    content(scope)
    LazyColumn {
        scope.valueHeader.value?.let {
            stickyHeader {
                Row {
                   it()
                }
            }
        }

        scope.valueRows.forEach {
            item{
                it()
            }
        }
    }

    return scope
}


class TableScope {
     val valueHeader: (MutableState<(@Composable () -> Unit)?>) = mutableStateOf(null)
     val valueRows = mutableStateListOf<@Composable () -> Unit>()

    @Composable
    fun header(content: @Composable () -> Unit) {
        LaunchedEffect(Unit){
            valueHeader.value = content
        }
    }

    @Composable
    fun row(content: @Composable () -> Unit) {
        LaunchedEffect(Unit) {
            valueRows.add(content)
        }
    }
}