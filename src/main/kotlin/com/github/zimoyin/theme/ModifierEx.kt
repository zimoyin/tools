package com.github.zimoyin.theme

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.zimoyin.utils.Observer


fun modifier() = Modifier

fun dividerModifier() = modifier().width(1.dp)
fun verticalDividerModifier() = modifier().fillMaxHeight().width(1.dp)
fun horizontalDividerModifier() = modifier().fillMaxWidth().width(1.dp)


fun Modifier.minHeight(height: Dp) = this.heightIn(height, Dp.Unspecified)
fun Modifier.minWidth(width: Dp) = this.widthIn(width, Dp.Unspecified)
fun Modifier.maxWidth(width: Dp) = this.widthIn(Dp.Unspecified, width)
fun Modifier.maxHeight(height: Dp) = this.heightIn(Dp.Unspecified, height)
fun Modifier.paddingTop(top: Dp) = this.padding(0.dp, top, 0.dp, 0.dp)
fun Modifier.paddingRight(right: Dp) = this.padding(0.dp, 0.dp, right, 0.dp)
fun Modifier.paddingBottom(bottom: Dp) = this.padding(0.dp, 0.dp, 0.dp, bottom)
fun Modifier.paddingLeft(left: Dp) = this.padding(left, 0.dp, 0.dp, 0.dp)
fun Modifier.paddingLeftAndRight(left: Dp, right: Dp = left) = this.padding(left, 0.dp, right, 0.dp)
fun Modifier.paddingTopAndBottom(top: Dp, bottom: Dp = top) = this.padding(0.dp, top, 0.dp, bottom)


fun Modifier.onHover(content: HoverScope.() -> Unit = {}): Modifier = this.pointerInput(Unit) {
    val scope = HoverScope()
    content(scope)
    awaitPointerEventScope {
        while (true) {
            val pointerEvent = awaitPointerEvent()
            if (pointerEvent.type == PointerEventType.Move) pointerEvent.changes.first().apply {
                scope.invertHook0()
            }

            if (pointerEvent.type == PointerEventType.Exit) {
                scope.invertHook1()
            }
        }
    }
}


class HoverScope {
    private val hook = Observer<() -> Unit>()

    fun onEntered(content: () -> Unit) = hook.add("0", content)
    fun onExited(content: () -> Unit) = hook.add("1", content)

    fun invertHook0() {
        hook.invertHook("0")
    }

    fun invertHook1() {
        hook.invertHook("1")
    }
}
