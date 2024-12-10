package com.example.tentcentproject.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tentcentproject.data.dao.PictureDao
import com.example.tentcentproject.data.model.Picture
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

class PicturePagingSource(
    private val pictureDao: PictureDao,
) : PagingSource<Int, Picture>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val position = params.key ?: 0
        val pictures = pictureDao.getItemsAfter(position, params.loadSize)
        return LoadResult.Page(
            data = pictures,
            prevKey = if (position == 0) null else position - 1,
            nextKey = if (pictures.isEmpty()) null else pictures.last().id+1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state.anchorPosition
    }
}