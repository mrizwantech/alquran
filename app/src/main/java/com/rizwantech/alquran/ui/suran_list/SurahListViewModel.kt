package com.rizwantech.alquran.ui.suran_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass
import com.rizwantech.alquran.network.NetworkInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SurahListViewModel : ViewModel() {


    private val mutableLiveDataSurahList = MutableLiveData<List<SurahDataClass>>()
    val surahListLiveSurahDataClass: LiveData<List<SurahDataClass>> = mutableLiveDataSurahList

   suspend fun setSurahList() {
        GlobalScope.launch(Dispatchers.IO) {
            val apiResponse = NetworkInterface().getSuraList().await()
            withContext(Dispatchers.Main) {
                mutableLiveDataSurahList.value = apiResponse.data

            }
        }

    }
}