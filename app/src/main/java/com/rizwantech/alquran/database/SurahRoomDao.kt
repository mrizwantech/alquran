package com.rizwantech.alquran.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass

@Dao
interface SurahRoomDao {
    @Query("SELECT * FROM surahdataclass")
    fun getAll(): List<SurahDataClass>

    @Query("SELECT * FROM surahdataclass WHERE surahIndex LIKE :surahIndex")
    fun findByTitle(surahIndex: String): LiveData<List<SurahDataClass>>

    @Insert
    fun insertAll(vararg surah: SurahDataClass)

}