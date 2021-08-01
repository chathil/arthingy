package com.example.uimain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.commonui.component.Spacer

@Composable
fun AgentCircle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("GO", style = MaterialTheme.typography.subtitle2, lineHeight = TextUnit.Unspecified, modifier = Modifier.padding(0.dp))
            }
        }
        Text(
            "Georgia O'Keefe",
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
            AgentCircle()
            Spacer(size = 8.dp)
            AgentCircle()
            Spacer(size = 8.dp)
            AgentCircle()
            Spacer(size = 8.dp)
            AgentCircle()
        }
    }
}
