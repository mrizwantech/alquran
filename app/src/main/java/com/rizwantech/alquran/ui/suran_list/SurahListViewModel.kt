package com.rizwantech.alquran.ui.suran_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizwantech.alquran.alqurandata.AyahsItem
import com.rizwantech.alquran.alqurandata.DataItem
import com.rizwantech.alquran.network.QuranApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SurahListViewModel : ViewModel() {
    private val mutableLiveDataSurahListName = MutableLiveData<List<DataItem>>()
    val surahListLiveSurahDataClassName: LiveData<List<DataItem>> = mutableLiveDataSurahListName
    private val mutableLiveDataAyahsItem = MutableLiveData<List<AyahsItem>>()
    val liveDataAyahsItem: LiveData<List<AyahsItem>> = mutableLiveDataAyahsItem
    suspend fun setSurahList() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = QuranApiService.invoke().getSuraList().await()
            withContext(Dispatchers.Main) {
                val listSurahName = apiResponse.data
                if (listSurahName != null && listSurahName.isNotEmpty()) {

                    mutableLiveDataSurahListName.value = listSurahName as List<DataItem>?
                }
            }
        }
    }

    suspend fun getSurahs(surahIndex: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val getSurahs = QuranApiService.invoke().getSurah(surahIndex).await()
            val ayaList = getSurahs.data?.ayahs
            CoroutineScope(Dispatchers.Main).launch {
                mutableLiveDataAyahsItem.value = ayaList as List<AyahsItem>?
            }
        }


    }


}