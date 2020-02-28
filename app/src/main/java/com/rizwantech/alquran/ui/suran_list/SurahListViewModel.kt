package com.rizwantech.alquran.ui.suran_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass
import com.rizwantech.alquran.database.SurahListDatabse
import com.rizwantech.alquran.network.NetworkInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SurahListViewModel : ViewModel() {


    private val mutableLiveDataSurahList = MutableLiveData<List<SurahDataClass>>()
    val surahListLiveSurahDataClass: LiveData<List<SurahDataClass>> = mutableLiveDataSurahList

    fun setSurahList(QuranApiService: NetworkInterface, db: SurahListDatabse?) {
        GlobalScope.launch(Dispatchers.Default) {
            val apiResponse = QuranApiService.getSuraList().await()

            for (surah in apiResponse.data) {
                db?.surahList()?.insertAll(surah)
            }
            withContext(Dispatchers.Main) {
                mutableLiveDataSurahList.value = apiResponse.data

            }
        }

    }
}