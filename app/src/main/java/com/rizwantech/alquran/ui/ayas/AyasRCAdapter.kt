package com.rizwantech.alquran.ui.ayas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizwantech.alquran.R
import com.rizwantech.alquran.alqurandata.AyahsItem
import com.rizwantech.alquran.ui.suran_list.SurahListRCAdapter
import kotlinx.android.synthetic.main.ayas_rc_adapter_layout.view.*
import kotlinx.android.synthetic.main.rc_adapter_surah_list.view.*

class AyasRCAdapter(private val ayahsItem: List<AyahsItem>) :
    RecyclerView.Adapter<AyasRCAdapter.AyahsItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahsItemViewHolder {
        return AyasRCAdapter.AyahsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.ayas_rc_adapter_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return ayahsItem.size
    }

    override fun onBindViewHolder(holder: AyahsItemViewHolder, position: Int) {
        holder.tvAyahsItem.text = ayahsItem[position].text
    }

    class AyahsItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvAyahsItem: TextView = v.ayas_text
    }
}