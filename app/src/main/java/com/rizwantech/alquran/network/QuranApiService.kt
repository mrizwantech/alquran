package com.rizwantech.alquran.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuranApiService {

    companion object Create {
        private const val BASE_URL = "http://api.alquran.cloud/v1/"
        fun invoke(): NetworkInterface {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY;
            val okHttpClientQuran = OkHttpClient.Builder()
            okHttpClientQuran.addInterceptor(interceptor)
            return Retrofit.Builder().client(okHttpClientQuran.build()).baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkInterface::class.java)
        }
    }
}