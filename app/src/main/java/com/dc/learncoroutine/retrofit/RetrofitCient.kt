package com.dc.learncoroutine.retrofit

import com.dc.learncoroutine.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    const val BASE_URL1: String = "http://dummy.restapiexample.com/api/v1/"
    const val BASE_URL2: String = "https://5e510330f2c0d300147c034c.mockapi.io/"
    private val okHttpClientBuilder = OkHttpClient.Builder()
    private val logInterceptor = HttpLoggingInterceptor()
    private lateinit var api: Api

    fun invoke(baseUrl : String,enableInterceptor: Boolean = true): Api {
        if (!::api.isInitialized) {
            if (enableInterceptor && BuildConfig.DEBUG) {
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            okHttpClientBuilder.addInterceptor(logInterceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
            api = retrofit.create(Api::class.java)
        }
        return api
    }
}