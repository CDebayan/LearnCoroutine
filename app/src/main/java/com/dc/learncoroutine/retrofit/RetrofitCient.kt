package com.dc.learncoroutine.retrofit

import com.dc.learncoroutine.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private val okHttpClientBuilder = OkHttpClient.Builder()
    private val logInterceptor = HttpLoggingInterceptor()

    fun invoke(enableInterceptor: Boolean = true): Api {
        if (enableInterceptor && BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        okHttpClientBuilder.addInterceptor(logInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()

        return retrofit.create(Api::class.java)
    }
}