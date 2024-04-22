package com.apf.core.image.loading.cache

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import javax.inject.Inject

const val TAG="MemoryCache"
class MemoryCache @Inject  constructor(newMaxSize: Int) :ImageCache {
    private val cache : LruCache<String, Bitmap>
    init {
        val cacheSize : Int
        if (newMaxSize > Config.maxMemory) {
            cacheSize = Config.defaultCacheSize
            Log.d(TAG,"New value of cache is bigger than maximum cache available on system")
        } else {
            cacheSize = newMaxSize
        }
        cache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return (value.rowBytes)*(value.height)/1024
            }
        }
    }

    override fun put(url: String, bitmap: Bitmap) {
        cache.put(url,bitmap)
    }

    override fun get(url: String): Bitmap? {
        return cache.get(url)
    }

    override fun clear() {
        cache.evictAll()
    }
}