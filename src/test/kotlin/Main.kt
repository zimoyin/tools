import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.singleWindowApplication
import com.github.zimoyin.theme.modifier
import com.github.zimoyin.ui.LeftAlignedColumn

fun main() = singleWindowApplication {
//    Row(
//        modifier = modifier().fillMaxSize(),
//        horizontalArrangement = Arrangement.Center,
//    ) {
//        Column {
//            Row(
//                modifier = modifier()
//                    .background(Color.Cyan),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Text("1")
//            }
//
//            Row(
//                modifier = modifier()
//                    .background(Color.Cyan),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Text("321")
//            }
//            Row(
//                modifier = modifier()
//                    .background(Color.Cyan),
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Text("74")
//            }
//        }
//    }


    LeftAlignedColumn(
        modifier = modifier().fillMaxSize(),

    ){
        Row(
            modifier = modifier()
                .background(Color.Cyan),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text("1")
        }

        Row(
            modifier = modifier()
                .background(Color.Cyan),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text("321")
        }
        Row(
            modifier = modifier()
                .background(Color.Cyan),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text("74")
        }
    }
}