package com.rizwantech.alquran.alqurandata.surahnameslist


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SurahDataClass(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "englishName") val englishName: String,
    @ColumnInfo(name = "englishNameTranslation") val englishNameTranslation: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surahIndex") val number: Int,
    @ColumnInfo(name = "numberOfAyahs") val numberOfAyahs: Int,
    @ColumnInfo(name = "revelationType") val revelationType: String
)