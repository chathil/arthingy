package com.example.uimain

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commonui.component.Spacer
import com.example.commonui.component.TextIcon

@Composable
fun Event(
    modifier: Modifier = Modifier
) {

    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.secondaryVariant,
        modifier = modifier
            .size(256.dp, 152.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(78.dp, 146.dp)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    color = MaterialTheme.colors.primaryVariant,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .size(86.dp, 162.dp)
                        .rotate(36f)
                        .offset(x = (-16).dp, y = (18).dp)
                ) {
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.offset(x = (-8).dp, y = (-4).dp)
                ) {
                    Text("MON", style = MaterialTheme.typography.subtitle1)
                    Text("31", style = MaterialTheme.typography.h3)
                }
            }
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .fillMaxHeight()
                    .clipToBounds(),
                contentAlignment = Alignment.TopCenter
            ) {
                Surface(
                    color = MaterialTheme.colors.background,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = (-14).dp)
                ) {
                }

                Canvas(
                    Modifier
                        .fillMaxWidth()
                        .height(112.dp)
                        .align(Alignment.Center)
                ) {
                    val rect = size.toRect()
                    drawLine(
                        color = Color.Gray,
                        start = rect.topCenter,
                        end = rect.bottomCenter,
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(
                                4.dp.toPx(),
                                4.dp.toPx(),
                                0f
                            )
                        ),
                        cap = StrokeCap.Round
                    )
                }

                Surface(
                    color = MaterialTheme.colors.background,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (14).dp)
                ) {
                }
            }
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                Text(
                    "Virtual Artist Talk: Jordan Casteel",
                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(4.dp)
                TextIcon(ic = painterResource(id = R.drawable.ic_fluent_ticket_diagonal_16_regular), label = "14:00-16:00")
                Spacer(4.dp)
                TextIcon(ic = painterResource(id = R.drawable.ic_fluent_location_16_regular), label = "14:00-16:00")
                Spacer(4.dp)
                TextIcon(ic = painterResource(id = R.drawable.ic_fluent_clock_16_regular), label = "14:00-16:00")
            }
        }
    }
}

@Preview
@Composable
private fun EventPreview() {
    Surface(Modifier.padding(8.dp)) {
        Event()
    }
}
