package com.example.uimain

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.artic.domain.model.AgentModel
import com.example.artic.domain.model.ArticImage
import com.example.artic.domain.model.ArtworkModel
import com.example.commonui.component.Spacer
import com.example.commonui.component.ViewComponent
import com.example.commonui.util.LocalSysUiController

enum class HomeViewComponent : ViewComponent {
    ARTWORK,
    ARTWORK_TYPE,
    AGENT,
    ENDLESS_ARTWORK,
    STORIES,
    NONE
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val artworks by viewModel.artworks().collectAsState(initial = emptyList())
    val artworkTypes by viewModel.artworkTypes().collectAsState(initial = emptyList())
    val pagedArtworks = viewModel.pagedArtworks().collectAsLazyPagingItems()
    val agents by viewModel.individualAgents().collectAsState(initial = emptyList())
    val audios by viewModel.audios().collectAsState(initial = emptyList())

    LocalSysUiController.current.setStatusBarColor(MaterialTheme.colors.primarySurface)
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeTopAppBar() }
    ) { innerPadding ->
        LazyColumn(state = lazyListState, modifier = Modifier.padding(innerPadding)) {
            item {
                Agents(agents = agents)
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp)
                ) {
                    SearchChip()
                    artworkTypes.forEach {
                        Spacer(8.dp)
                        Chip(text = it.title)
                    }
                }
            }
            item {
                Artworks(artworks = artworks)
            }
            item {
                Divider(modifier = Modifier.padding(vertical = 16.dp))
            }
            item {
                Text(
                    "Stories",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 8.dp
                    )
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    audios.forEach { audio ->
                        Spacer(16.dp)
                        Story(audio = audio)
                    }
                    Spacer(32.dp)
                }
            }
            item {
                Divider(modifier = Modifier.padding(vertical = 16.dp))
            }
            item {
                Text(
                    "Events",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 8.dp
                    )
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Spacer(16.dp)
                    Event()
                    Spacer(16.dp)
                    Event()
                    Spacer(16.dp)
                    Event()
                    Spacer(16.dp)
                    Event()
                    Spacer(32.dp)
                }
            }
            item {
                Spacer(64.dp)
            }
            grid(pagedArtworks) {
                ArtworkGrid(it)
            }
        }
    }
}

// TODO: 01/08/21 : The ripple effect is still in rectangle
@Composable
fun SearchChip(
    modifier: Modifier = Modifier,
    onTap: () -> Unit = {}
) {
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
        contentColor = MaterialTheme.colors.onSurface,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.clickable { onTap() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_fluent_search_24_regular),
            contentDescription = "Search Button",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}

@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier
) {
    val title = stringResource(id = R.string.app_name)

    ExpandedTopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fluent_person_24_filled),
                    contentDescription = stringResource(R.string.user)
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun Artworks(modifier: Modifier = Modifier, artworks: List<ArtworkModel>) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        artworks.forEach { artwork ->
            Spacer(16.dp)
            Artwork(
                artwork = artwork
            )
        }
        Spacer(32.dp)
    }
}

@Composable
fun Agents(modifier: Modifier = Modifier, agents: List<Pair<AgentModel, String?>>) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Spacer(size = 16.dp)
        agents.forEach { agent ->
            AgentCircle(agent = agent)
        }
        Spacer(size = 8.dp)
    }
}

@Composable
private fun ArtworkGrid(artworkPair: Pair<ArtworkModel?, ArtworkModel?>) {
    val (left, right) = artworkPair
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            rememberImagePainter(
                data = ArticImage(left?.imageId).requestUrl()
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .weight(.5f)
                .height(132.dp)
        )
        Image(
            rememberImagePainter(
                data = ArticImage(right?.imageId).requestUrl()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(.5f)
                .height(132.dp)
        )
    }
    Spacer(2.dp)
}

@ExperimentalFoundationApi
fun <T : Any> LazyListScope.grid(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: Pair<T?, T?>) -> Unit
) {

    val pairs = lazyPagingItems.snapshot().windowed(2, 2).map {
        Pair(it[0], it[1])
    }

    items(pairs.size) { index ->
        itemContent(pairs[index])
    }
}

// TODO: 04/08/21 Fix HomeTopAppBarPreview
// @Preview
// @Composable
// private fun HomeTopAppBarPreview() {
//    HomeTopAppBar(scrollOffset = .7f, agents = LazyPagingItems<AgentModel>(AgentModel.fakes))
// }

// {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(innerPadding)
//    ) {
//        item {
//            Spacer(Modifier.padding(16.dp))
//        }
//
//        items(artworks) { artwork ->
//            Text(artwork?.title ?: "Unknown Arts")
//            Spacer(modifier = Modifier.size(32.dp))
//        }
//
//        item {
//            Spacer(Modifier.padding(64.dp))
//        }
//    }
// }
