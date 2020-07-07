package com.rizwantech.alquran.ui.suran_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.rizwantech.alquran.R
import com.rizwantech.alquran.alqurandata.DataItem
import com.rizwantech.alquran.alqurandata.SurahList
import kotlinx.android.synthetic.main.rc_adapter_surah_list.view.*

class SurahListRCAdapter(private val surahNamesList: List<DataItem>) :
    RecyclerView.Adapter<SurahListRCAdapter.SurahListViewHolder>() {
    private val mutableLiveDataClickedPosition = MutableLiveData<Int>()
    val surahListLiveClickedPosition: LiveData<Int> = mutableLiveDataClickedPosition
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SurahListViewHolder {
        return SurahListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rc_adapter_surah_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return surahNamesList.size
    }

    override fun onBindViewHolder(holder: SurahListViewHolder, position: Int) {
        holder.tvSurahNameEnglish.text = surahNamesList[position].englishName
        holder.tvSurahNameArabic.text = surahNamesList[position].name
        holder.tvSurahCity.text = surahNamesList[position].revelationType
        holder.tvEnglishTranslation.text = surahNamesList[position].englishNameTranslation
        holder.itemView.setOnClickListener {
           mutableLiveDataClickedPosition.value= position+1
        }
    }

    class SurahListViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvSurahNameEnglish: TextView = v.surah_name_english
        val tvSurahNameArabic: TextView = v.surah_name_arabic
        val tvSurahCity: TextView = v.surah_place
        val tvEnglishTranslation: TextView = v.surah_verses_count

    }


}