package com.project.apf.data

import com.project.apf.network.models.MediaItem
import com.project.apf.network.models.MediaResponse
import com.skydoves.sandwich.ApiResponse

interface ApfRepo {
    suspend fun fetchMedia(limit:Int): ApiResponse<List<MediaItem>>
}