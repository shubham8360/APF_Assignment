package com.apf.core.image.loading.photon

import android.graphics.Bitmap
import android.widget.ImageView
import com.apf.core.image.loading.async.DownloadImageTask
import com.apf.core.image.loading.async.DownloadTask
import com.apf.core.image.loading.cache.CacheRepository
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject

class Photon @Inject  constructor(private val cache: CacheRepository) {
    private val executorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val mRunningDownloadList: HashMap<String, Future<Bitmap?>> = hashMapOf()

    fun displayImage(url: String, imageview: ImageView, placeholder: Int) {
        val bitmap = cache.get(url)
        bitmap?.let {
            imageview.setImageBitmap(it)
            return
        }
            ?: run {
                imageview.tag = url
//                imageview.setImageResource(placeholder)
                addDownloadImageTask(url, DownloadImageTask(url, imageview, cache))
            }

    }

    private fun addDownloadImageTask(url: String, downloadTask: DownloadTask<Bitmap?>) {

        mRunningDownloadList[url] = executorService.submit(downloadTask)
    }

    private fun clearCache() {
        cache.clear()
    }

    private fun cancelTask(url: String) {
        synchronized(this) {
            mRunningDownloadList.forEach {
                if (it.key == url && !it.value.isDone)
                    it.value.cancel(true)
            }
        }
    }

    private fun cancelAll() {
        synchronized(this) {
            mRunningDownloadList.forEach {
                if (!it.value.isDone)
                    it.value.cancel(true)
            }
            mRunningDownloadList.clear()
        }
    }
}