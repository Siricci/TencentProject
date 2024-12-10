package com.example.tentcentproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tentcentproject.data.model.Picture
import com.example.tentcentproject.ui.theme.TentcentProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("Activity").d("onCreate")
        enableEdgeToEdge()
        setContent {
            TentcentProjectTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Top app bar")
                            },
                            actions = {
                                Row {
                                    IconButton(onClick = {
                                        // TODO refresh
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_filter_button),
                                            contentDescription = "refresh"
                                        )
                                    }
                                    ImagePickerButton { selectedUri ->
                                        Timber.tag("Activity").d("selectedUri: $selectedUri")
                                        viewModel.insert(selectedUri)
                                    }
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val lazyPagingItems: LazyPagingItems<Picture> =
                        viewModel.getItems().collectAsLazyPagingItems()

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 设置列数为2
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(lazyPagingItems.itemCount) { index ->
                            val picture = lazyPagingItems[index]

                            picture?.let {
                                val imageUri = remember(picture.uri) { picture.uri }

                                PictureItem(imageUri = picture.uri)
                            }
                        }
                    }
                }
            }
        }
    }
}