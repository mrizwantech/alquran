package com.rizwantech.alquran.ui.suran_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizwantech.alquran.R
import com.rizwantech.alquran.alqurandata.surahnameslist.SurahDataClass
import com.rizwantech.alquran.database.SurahListDatabse
import com.rizwantech.alquran.network.NetworkInterface


class SurahListFragment : Fragment() {

    private lateinit var surahListViewModel: SurahListViewModel
    private lateinit var adapter: SurahListRCAdapter
    private lateinit var recylerview: RecyclerView
    private lateinit var listSurah: List<SurahDataClass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        surahListViewModel =
            ViewModelProviders.of(this).get(SurahListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_surahs, container, false)
        recylerview= root.findViewById(R.id.rc_surah)
        recylerview.setHasFixedSize(true)
        val db= container?.context?.let { SurahListDatabse(it) };
        surahListViewModel.setSurahList(NetworkInterface(),db)
        surahListViewModel.surahListLiveSurahDataClass.observe(this, Observer { surahList ->
            Log.d("SurahName", surahList[0].englishName)
            listSurah=surahList
            initAdater()
        })


        return root
    }

    private fun initAdater() {
        val layoutmanager = LinearLayoutManager(context)
        recylerview.layoutManager = layoutmanager
        recylerview.addItemDecoration(
            DividerItemDecoration(
                recylerview.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = SurahListRCAdapter(surahListViewModel.surahListLiveSurahDataClass.value!!)
        recylerview.adapter = adapter
    }
}