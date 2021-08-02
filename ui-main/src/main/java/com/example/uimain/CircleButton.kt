package com.example.uimain

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        elevation = 4.dp, shape = CircleShape,
        modifier = modifier
            .size(32.dp),
        content = content
    )
}

@Preview
@Composable
fun CircleButtonPreview() {
    Surface {
        Row {
            repeat(4) {
                CircleButton {
                    Icon(
                        painter = painterResource(R.drawable.ic_fluent_share_16_regular),
                        contentDescription = "Share",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
