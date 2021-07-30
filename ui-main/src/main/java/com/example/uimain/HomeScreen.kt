package com.example.uimain

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.commonui.component.InsetAwareTopAppBar

enum class HomeViewComponent {
//    PET_LIST
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val artworks = viewModel.artworks().collectAsLazyPagingItems()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            val title = stringResource(id = R.string.app_name)
            InsetAwareTopAppBar(
                title = { Text(text = title) },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = stringResource(R.string.user)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Spacer(Modifier.padding(16.dp))
            }

            items(artworks) { artwork ->
                Text(artwork?.title ?: "Unknown Arts")
                Spacer(modifier = Modifier.size(32.dp))
            }

            item {
                Spacer(Modifier.padding(64.dp))
            }
        }
    }
}
