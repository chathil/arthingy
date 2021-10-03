package com.example.uimain

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.artic.domain.model.AudioModel

@Composable
fun Story(
    modifier: Modifier = Modifier,
    audio: AudioModel
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
        modifier = modifier
            .width(256.dp)
            .height(192.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fluent_play_32_regular),
                    contentDescription = "Play",
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    audio.artworkTitle,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Slider(value = .5f, onValueChange = {}, modifier.padding(0.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 6.dp)
                    .offset(y = (-16).dp)
            ) {
                Text("2:30", style = MaterialTheme.typography.caption)
                Text("5:00", style = MaterialTheme.typography.caption)
            }
            StoryAction()
        }
    }
}

@Composable
private fun StoryAction(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.wrapContentSize()
    ) {
        CircleButton {
            Icon(
                painter = painterResource(R.drawable.ic_fluent_share_16_regular),
                contentDescription = "Share",
                modifier = Modifier.padding(8.dp)
            )
        }
        CircleButton {
            Icon(
                painter = painterResource(R.drawable.ic_fluent_heart_16_regular),
                contentDescription = "Share",
                modifier = Modifier.padding(8.dp)
            )
        }
        CircleButton {
            Icon(
                painter = painterResource(R.drawable.ic_fluent_more_horizontal_16_regular),
                contentDescription = "Share",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// TODO: 26/09/21 fix preview
// @Preview
// @Composable
// private fun StoryPreview() {
//    Surface(Modifier.padding(8.dp)) {
//        Story()
//    }
// }
