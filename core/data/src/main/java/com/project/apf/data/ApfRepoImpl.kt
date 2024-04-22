package com.project.apf.data

import com.project.apf.network.api.ApfApi
import com.project.apf.network.models.MediaItem
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class ApfRepoImpl @Inject constructor(private val api: ApfApi) : ApfRepo {
    override suspend fun fetchMedia(limit: Int): ApiResponse<List<MediaItem>> =
        api.fetchMedia(limit)
}