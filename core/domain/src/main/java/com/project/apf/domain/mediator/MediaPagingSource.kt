package com.project.apf.domain.mediator

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.apf.data.ApfRepo
import com.project.apf.domain.PAGE_SIZE
import com.project.apf.network.models.MediaItem
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class MediaPagingSource @Inject constructor(private val repo: ApfRepo) :
    PagingSource<Int, MediaItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaItem> {
        val nextPageNumber = params.key ?: 1
        var result: LoadResult<Int, MediaItem> = LoadResult.Invalid()
        repo.fetchMedia(nextPageNumber * PAGE_SIZE).suspendOnSuccess {
            result = LoadResult.Page(
                data = data.takeLast(nextPageNumber * PAGE_SIZE),
                prevKey = null,
                nextKey = nextPageNumber+1
            )
        }.suspendOnException {
            result = LoadResult.Error(throwable)
        }.suspendOnFailure {
            result = LoadResult.Error(Throwable(message()))
        }
        return result
    }

    override fun getRefreshKey(state: PagingState<Int, MediaItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}