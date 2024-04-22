package com.apf.core.image.loading.async

import java.util.concurrent.Callable

abstract class DownloadTask<T> : Callable<T> {
    abstract fun downloadBitmap(urlStr: String): T
}