package com.project.apf.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaItem(
    val backupDetails: BackupDetails?,
    val coverageURL: String,
    val id: String,
    val language: String,
    val mediaType: Int,
    val publishedAt: String,
    val publishedBy: String,
    val thumbnail: Thumbnail,
    val title: String
)

@JsonClass(generateAdapter = true)
data class MediaResponse(val data: List<MediaItem>)