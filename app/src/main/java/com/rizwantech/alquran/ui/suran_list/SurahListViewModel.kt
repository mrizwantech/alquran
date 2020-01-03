package com.rizwantech.alquran.ui.suran_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass
import com.rizwantech.alquran.database.RepositorySurahList
import com.rizwantech.alquran.database.SurahListDatabse
import com.rizwantech.alquran.network.NetworkQuranInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SurahListViewModel : ViewModel() {


    private val isDataReceived: MutableLiveData<Boolean> = MutableLiveData()
    private var surahListMutableList: MutableLiveData<List<SurahDataClass>> = MutableLiveData()

     fun saveSurahList(db: SurahListDatabse) {
        var surahArray: List<SurahDataClass>
        isDataReceived.value = true
        val repo = RepositorySurahList()
        surahArray = repo.getSurahList(db)
        GlobalScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {
                surahListMutableList.value = surahArray
            }

        }
    }

    fun getSurahList(): LiveData<List<SurahDataClass>> {
        return surahListMutableList
    }

}