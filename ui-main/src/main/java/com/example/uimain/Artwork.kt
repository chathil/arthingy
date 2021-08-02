package com.example.uimain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.commonui.component.Spacer

@Composable
fun Artwork(
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            elevation = 16.dp,
            color = MaterialTheme.colors.primaryVariant,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.size(216.dp, 176.dp)
        ) {}
        Spacer(size = 16.dp)
        ArtworkAction()
        Spacer(size = 6.dp)
        Text(
            "Georgia O'Keeffe",
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold)
        )
        Text("Blue and Green Music", style = MaterialTheme.typography.body1)
        Text("United States", style = MaterialTheme.typography.caption)
    }
}

@Composable
private fun ArtworkAction(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CircleButton {
            Surface(color = MaterialTheme.colors.primarySurface, content = {})
        }
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

@Preview
@Composable
private fun ArtworkPreview() {
    Artwork()
}

@Preview
@Composable
private fun ArtworkActionPreview() {
    Surface {
        ArtworkAction(modifier = Modifier.padding(8.dp))
    }
}
