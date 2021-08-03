package com.example.uimain

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.commonui.component.Spacer
import com.example.commonui.util.LocalSysUiController
import kotlin.math.min

enum class HomeViewComponent {
//    PET_LIST
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val threshold = 600f
//    val artworks = viewModel.artworks().collectAsLazyPagingItems()
    LocalSysUiController.current.setStatusBarColor(
        MaterialTheme.colors.primarySurface
    )
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()
    val scrollOffset: Float = min(
        1f,
        1 - (lazyListState.firstVisibleItemScrollOffset / threshold +
                lazyListState.firstVisibleItemIndex)
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopAppBar(scrollOffset = scrollOffset)
        }
    ) { innerPadding ->
        LazyColumn(state = lazyListState, modifier = Modifier.padding(innerPadding)) {
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
                    Spacer(size = 8.dp)
                    Chip(text = "Materials")
                    Spacer(size = 8.dp)
                    Chip(text = "Paintings")
                    Spacer(size = 8.dp)
                    Chip(text = "Time Based Media")
                    Spacer(size = 8.dp)
                    Chip(text = "Equipment")
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Spacer(16.dp)
                    Artwork()
                    Spacer(16.dp)
                    Artwork()
                    Spacer(16.dp)
                    Artwork()
                    Spacer(16.dp)
                    Artwork()
                    Spacer(32.dp)
                }
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
                    Spacer(16.dp)
                    Story()
                    Spacer(16.dp)
                    Story()
                    Spacer(16.dp)
                    Story()
                    Spacer(16.dp)
                    Story()
                    Spacer(16.dp)
                    Story()
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
    modifier: Modifier = Modifier,
    scrollOffset: Float
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
        expandedContent = {
            AgentHeader(
                scrollOffset = scrollOffset,
                modifier = Modifier.wrapContentHeight()
            )
        },
        scrollOffset = scrollOffset,
        modifier = modifier
    )
}

@Composable
fun AgentHeader(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    scrollOffset: Float,
) {
    val imageSize by animateDpAsState(targetValue = max(0.dp, 132.dp * scrollOffset))
    Surface(
        color = backgroundColor,
        modifier = modifier
            .height(imageSize)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
                .background(Color.Transparent)
        ) {
            Spacer(size = 16.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 8.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 8.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 8.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 8.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 8.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 8.dp)
            AgentCircle(scrollOffset = scrollOffset)
            Spacer(size = 16.dp)
        }
    }
}

@Preview
@Composable
private fun HomeTopAppBarPreview() {
    HomeTopAppBar(scrollOffset = .7f)
}

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
