package com.project.apf.network.di

import com.project.apf.core.network.BuildConfig
import com.project.apf.network.api.ApfApi
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object AppModules {
    const val BASE_URL = "https://acharyaprashant.org/api/v2/"


    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()



    @Provides
    @Singleton
    fun provideNetworkClient(okHttpCallFactory: dagger.Lazy<Call.Factory>) = Retrofit.Builder()
        .baseUrl(BASE_URL)

        .callFactory { okHttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            MoshiConverterFactory.create()
        ).addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
        .create(ApfApi::class.java)


}