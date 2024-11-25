package com.example.tentcentproject.gallery


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.tentcentproject.R
import com.example.tentcentproject.ui.theme.TentcentProjectTheme
@Composable
fun ImageGallery(imageResIds: List<Int>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 96.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        modifier = modifier.fillMaxSize()
    ) {
        items(imageResIds.size) { index ->
            val imageResId = imageResIds[index]
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .size(128.dp, 128.dp),
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = stringResource(R.string.description),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageGalleryPreview() {
    TentcentProjectTheme {
        ImageGallery(
            imageResIds = List(20) { R.drawable.sample }
        )
    }
}