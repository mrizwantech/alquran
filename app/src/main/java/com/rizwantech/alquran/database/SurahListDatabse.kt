package com.rizwantech.alquran.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass

@Database(entities = [SurahDataClass::class], version = 1)
abstract class SurahListDatabse : RoomDatabase() {
    abstract fun surahList(): SurahRoomDao

    companion object {
        @Volatile
        private var instance: SurahListDatabse? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            SurahListDatabse::class.java, "surah-list.db"
        )
            .build()
    }
}