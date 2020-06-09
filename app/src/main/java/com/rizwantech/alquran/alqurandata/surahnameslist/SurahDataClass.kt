package com.rizwantech.alquran.alqurandata.surahnameslist


data class SurahDataClass(

    var id: Int,
    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val number: Int,
    val numberOfAyahs: Int,
    val revelationType: String
)