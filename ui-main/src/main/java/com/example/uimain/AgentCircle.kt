package com.example.uimain

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.artic.domain.model.AgentModel

@Composable
fun AgentCircle(
    modifier: Modifier = Modifier,
    agent: Pair<AgentModel, String?>?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .size(96.dp)
                .padding(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    agent?.first?.title?.substring(0..2) ?: "N/A",
                    style = MaterialTheme.typography.subtitle2,
                    lineHeight = TextUnit.Unspecified,
                    modifier = Modifier.padding(0.dp)
                )
            }
            Image(
                painter = rememberImagePainter(
                    data = agent?.second,
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = null,
            )
        }

        Text(
            agent?.first?.title ?: "Unknown Agent",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.width(72.dp)
        )
    }
}

@Preview
@Composable
fun AgentCirclePreview() {
    Surface {
        Row(modifier = Modifier.padding(16.dp)) {
            AgentModel.fakes.forEach {
                AgentCircle(agent = it to "")
            }
        }
    }
}
