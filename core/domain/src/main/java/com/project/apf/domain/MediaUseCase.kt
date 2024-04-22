package com.project.apf.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.project.apf.data.ApfRepo
import com.project.apf.domain.mediator.MediaPagingSource
import com.project.apf.network.result.Result
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

const val PAGE_SIZE=80
class MediaUseCase @Inject constructor(private val repo: ApfRepo) {
    operator fun invoke() = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = PAGE_SIZE)
    ) {
        MediaPagingSource(repo)
    }.flow
}