package com.rizwantech.alquran.network

import com.rizwantech.alquran.alqurandata.SurahList
import com.rizwantech.alquran.alqurandata.Surahs
import kotlinx.coroutines.Deferred

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NetworkInterface {

    @GET("surah")
    fun getSuraList(): Deferred<SurahList>

    @GET("surah/{surahIndex}")
    fun getSurah(@Path("surahIndex") surahIndex: Int): Deferred<Surahs>
}