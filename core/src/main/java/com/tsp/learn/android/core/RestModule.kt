package com.tsp.learn.android.core

import android.app.Application
import com.tsp.learn.android.core.service.SearchService
import com.tsp.learn.android.core.utils.CacheInterceptor
import com.tsp.learn.android.core.utils.Constants
import com.tsp.learn.android.core.utils.OffLineInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class RestModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideHttpCache(): Cache {
        return Cache(application.cacheDir, Constants.CACHE_SIZE.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.apply {
            addNetworkInterceptor(CacheInterceptor())
            addInterceptor(OffLineInterceptor(application))
            cache(cache)
            addInterceptor(interceptor)
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(Constants.BASE_API_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideNasaApiService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}