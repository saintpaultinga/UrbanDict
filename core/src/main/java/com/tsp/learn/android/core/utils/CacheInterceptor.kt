package com.tsp.learn.android.core.utils

import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-rapidapi-key", Constants.APP_TOKEN)
            .build()
        val originalResponse = chain.proceed(request)
        val cacheControl = originalResponse.header("Cache-Control")
        return if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains(
                "no-cache"
            ) ||
            cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
        ) {
            originalResponse.newBuilder()
                .header(
                    "Cache-Control",
                    "public, max-age=" + Constants.MAX_AGE
                )
                .build()
        } else {
            originalResponse
        }
    }
}