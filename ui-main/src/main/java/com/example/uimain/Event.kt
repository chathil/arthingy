package com.example.uimain

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commonui.component.Spacer

@Composable
fun Event(
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
        modifier = modifier
            .size(256.dp, 152.dp)
            .wrapContentHeight()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
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
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                Text(
                    "Virtual Artist Talk: Jordan Casteel",
                    style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(4.dp)
                Text(
                    "Jordan Casteel. Barack, 2020. Rennie Collection. Courtesy the artist and Casey Kaplan, New York. © Jordan Casteel. Photo by David Schulze.",
                    style = MaterialTheme.typography.caption,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(8.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fluent_clock_16_regular),
                        contentDescription = "Event time"
                    )
                    Text("14:00-16:00", style = MaterialTheme.typography.caption)
                    Chip(text = "Buy")
                }
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
