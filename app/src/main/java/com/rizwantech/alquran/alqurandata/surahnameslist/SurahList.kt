package com.rizwantech.alquran.alqurandata.surahnameslist


data class SurahList(
    val code: Int,
    val `data`: List<SurahDataClass>,
    val status: String
)