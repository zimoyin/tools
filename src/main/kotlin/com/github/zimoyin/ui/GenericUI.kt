package com.github.zimoyin.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.zimoyin.theme.horizontalDividerModifier
import com.github.zimoyin.theme.onHover
import com.github.zimoyin.theme.modifier
import com.github.zimoyin.theme.verticalDividerModifier


/**
 * 文本按钮
 */
@Composable
fun TextButton(
    text: String = "Button",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        Text(text)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IconButtonByImage(
    imageVector: ImageVector,
    contentDescription: String? = imageVector.name,
    modifier: Modifier = Modifier,
    startTint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    endTint: Color = Color.Cyan.copy(alpha = 0.5f),
    onClick: () -> Unit = {},
) {
    val color = remember { mutableStateOf(startTint) }
    Icon(
        modifier = modifier
            .onHover {
                onEntered {
                    color.value = endTint
                }
                onExited {
                    color.value = startTint
                }
            }
            .onClick { onClick() },
        imageVector = imageVector,
        tint = color.value,
        contentDescription = contentDescription,
    )
}

/**
 * 水平分割线
 */
@Composable
fun HorizontalDivider(
    modifier: Modifier = horizontalDividerModifier(),
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp,
) = Divider(
    modifier = modifier,
    color = color,
    thickness = thickness,
    startIndent = startIndent,
)

/**
 * 垂直分割线
 */
@Composable
fun VerticalDivider(
    modifier: Modifier = verticalDividerModifier(),
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp,
) = Divider(
    modifier = modifier,
    color = color,
    thickness = thickness,
    startIndent = startIndent,
)

@Composable
fun Spacer(height: Dp = 10.dp, width: Dp = 0.dp) {
    Spacer(modifier().height(height).width(width))
}


@Deprecated("不能适用于大部分情况")
@Composable
fun LeftAlignedColumn(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable ColumnScope.() -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        Column(
            content = content
        )
    }

}

@Composable
fun RowScope.RowDivider(
    width: Dp = 1.dp,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp,
) {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .then(modifier)
            .width(width),
        color = color,
        thickness = thickness,
        startIndent = startIndent,
    )
}