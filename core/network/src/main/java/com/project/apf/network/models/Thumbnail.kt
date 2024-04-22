package com.project.apf.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val aspectRatio: Double,
    val basePath: String,
    val domain: String,
    val id: String,
    val key: String,
    val qualities: List<Int>,
    val version: Int
){
    //    1 imageURL = domain + "/" + basePath + "/0/" + key
    val thumbNailUrl:String get() = "$domain/$basePath/0/$key"

}