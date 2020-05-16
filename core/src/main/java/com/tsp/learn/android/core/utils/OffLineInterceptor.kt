package com.tsp.learn.android.core.utils

import android.content.Context
import android.util.Log
import com.tsp.learn.android.core.utils.NetWorkUtils.isOnline
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OffLineInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (!isOnline(context)) {
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=${Constants.MAX_STALE}")
                .build()
        }
        return chain.proceed(request)
    }

}