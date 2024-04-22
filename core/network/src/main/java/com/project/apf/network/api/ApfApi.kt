package com.project.apf.network.api

import com.project.apf.network.models.MediaItem
import com.project.apf.network.models.MediaResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApfApi {

    @GET("content/misc/media-coverages")
    suspend fun fetchMedia(@Query("limit") limit:Int):ApiResponse<List<MediaItem>>
}