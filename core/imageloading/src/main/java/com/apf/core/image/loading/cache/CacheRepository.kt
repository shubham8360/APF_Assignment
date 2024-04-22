package com.apf.core.image.loading.cache

import android.graphics.Bitmap
import javax.inject.Inject

class CacheRepository @Inject constructor(
    private val diskCache: DiskCache,
    private val memoryCache: MemoryCache,
) : ImageCache {

    override fun put(url: String, bitmap: Bitmap) {
        memoryCache.put(url, bitmap)
        diskCache.put(url, bitmap)
    }

    override fun get(url: String): Bitmap? = memoryCache.get(url) ?: diskCache.get(url)


    override fun clear() {
        memoryCache.clear()
        diskCache.clear()
    }
}