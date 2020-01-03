package com.rizwantech.alquran.ui.suran_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizwantech.alquran.R
import com.rizwantech.alquran.database.RepositorySurahList
import com.rizwantech.alquran.database.SurahListDatabse
import com.rizwantech.alquran.network.NetworkQuranInterface


class SurahListFragment : Fragment() {

    private lateinit var surahListViewModel: SurahListViewModel
    private lateinit var adapter: SurahListRCAdapter
    private lateinit var recylerview: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        surahListViewModel =
            ViewModelProviders.of(this).get(SurahListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_surahs, container, false)
        recylerview = root.findViewById(R.id.rc_surah)
        progressBar = root.findViewById(R.id.pb_surah_list)
        recylerview.setHasFixedSize(true)

        val db: SurahListDatabse? = container?.context?.let { SurahListDatabse(it) }
        db?.let { surahListViewModel.saveSurahList(it) }
        surahListViewModel.getSurahList().observe(this, Observer {
            initAdapter()
            adapter.notifyDataSetChanged()
        })

        return root
    }

    private fun initAdapter() {
        val layoutmanager = LinearLayoutManager(context)
        adapter = SurahListRCAdapter(surahListViewModel.getSurahList().value!!)
        recylerview.layoutManager = layoutmanager
        recylerview.addItemDecoration(
            DividerItemDecoration(
                recylerview.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        recylerview.adapter = adapter
    }
}