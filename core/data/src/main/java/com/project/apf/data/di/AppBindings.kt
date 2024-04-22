package com.project.apf.data.di

import com.project.apf.data.ApfRepo
import com.project.apf.data.ApfRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindings {

    @Binds
    internal abstract fun bindRepo(repo: ApfRepoImpl): ApfRepo
}