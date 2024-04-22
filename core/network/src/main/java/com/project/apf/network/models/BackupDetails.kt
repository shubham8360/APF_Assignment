package com.project.apf.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BackupDetails(
    val pdfLink: String,
    val screenshotURL: String
)