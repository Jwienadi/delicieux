package com.android.delicieuxapp.API

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest: Request = chain.request()
        //2b0724f8aec25e2946034f3e7dcb4920 apiammar
        //0d9669f4a2ef9bab2589dda088256b93 apijessica
        val request: Request = interceptedRequest.newBuilder()
            .header(KEY_AUTHORIZATION, "2b0724f8aec25e2946034f3e7dcb4920")
            .method(interceptedRequest.method, interceptedRequest.body)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val KEY_AUTHORIZATION = "user_key"
    }
}