package com.rizwantech.alquran.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahList
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkInterface {

    @GET("surah")
    fun getSuraList():Deferred<SurahList>
    companion object {
        operator fun invoke():NetworkInterface{
             val authInterceptor = Interceptor {chain->
                val newUrl = chain.request().url()
                    .newBuilder()

                    .build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
            val okHttpClientQuran = OkHttpClient().newBuilder()
                .addInterceptor(authInterceptor)
                .build()
            return Retrofit.Builder().client(okHttpClientQuran).baseUrl("http://api.alquran.cloud/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkInterface::class.java)
        }
    }
}