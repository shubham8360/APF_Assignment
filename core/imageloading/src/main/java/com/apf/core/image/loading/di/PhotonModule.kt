package com.apf.core.image.loading.di

import android.content.Context
import com.apf.core.image.loading.cache.Config
import com.jakewharton.disklrucache.DiskLruCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PhotonModule {
    @Provides
    @Singleton
    fun provideCacheSize(): Int = Config.defaultCacheSize


}