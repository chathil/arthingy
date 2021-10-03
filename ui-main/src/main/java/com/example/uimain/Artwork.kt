package com.example.uimain

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.artic.domain.model.ArticImage
import com.example.artic.domain.model.ArtworkModel
import com.example.commonui.component.Spacer

@Composable
fun Artwork(
    modifier: Modifier = Modifier,
    artwork: ArtworkModel
) {
    val width = 216.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.width(width)) {
        Surface(
            elevation = 16.dp,
            color = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.size(width, 176.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = ArticImage(artwork.imageId).requestUrl()
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .padding(8.dp)
            )
        }

        Spacer(size = 16.dp)
        ArtworkAction(artwork = artwork)
        Spacer(size = 6.dp)
        Text(
            artwork.artistTitle,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            artwork.title,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            artwork.placeOfOrigin,
            style = MaterialTheme.typography.caption,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ArtworkAction(
    modifier: Modifier = Modifier,
    artwork: ArtworkModel
) {

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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

@Composable
fun ArtworkImage(
    modifier: Modifier = Modifier,
    artwork: ArtworkModel
) {
}

@Preview
@Composable
private fun ArtworkPreview() {
//    Artwork()
}

@Preview
@Composable
private fun ArtworkActionPreview() {
    Surface {
//        ArtworkAction(modifier = Modifier.padding(8.dp))
    }
}
