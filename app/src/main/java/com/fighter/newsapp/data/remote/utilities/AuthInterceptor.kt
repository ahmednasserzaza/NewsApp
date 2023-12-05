package com.fighter.newsapp.data.remote.utilities

import com.fighter.newsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader(AUTHORIZATION, "Bearer ${BuildConfig.API_KEY}")
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}