package com.rizwantech.alquran.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass
import com.rizwantech.alquran.network.NetworkQuranInterface
import kotlinx.coroutines.*
import java.net.NetworkInterface


class RepositorySurahList {
    var surahArrayList: List<SurahDataClass> = ArrayList()

    fun getSurahList(db: SurahListDatabse): List<SurahDataClass> {

        runBlocking {
            launch(Dispatchers.Default) {

                surahArrayList = db.surahList().getAll()

                if (surahArrayList.isEmpty()) {
                    val quranApiService = NetworkQuranInterface()
                    val apiResponce = quranApiService.getSuraList().await()

                    for (surah in apiResponce.data) {
                        db.surahList().insertAll(surah)
                    }
                    surahArrayList = apiResponce.data

                }


            }
        }

        return surahArrayList
    }

}