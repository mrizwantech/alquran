package com.rizwantech.alquran.ui.suran_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizwantech.alquran.R
import com.rizwantech.alquran.alqurandata.DataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SurahListFragment : Fragment() {

    private lateinit var surahListViewModel: SurahListViewModel
    private lateinit var adapter: SurahListRCAdapter
    private lateinit var recylerview: RecyclerView
    private lateinit var listSurah: List<DataItem>
    private lateinit var navigation: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        surahListViewModel =
            ViewModelProvider(this).get(SurahListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_surahs, container, false)
        recylerview = root.findViewById(R.id.rc_surah)
        recylerview.setHasFixedSize(true)
        CoroutineScope(Dispatchers.Main).launch {
            surahListViewModel.setSurahList()
        }
        surahListViewModel.surahListLiveSurahDataClassName.observe(
            viewLifecycleOwner,
            Observer { surahList ->
                listSurah = surahList
                initAdapter()
            })
        navigation = findNavController(this)
        return root
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(context)
        recylerview.layoutManager = layoutManager
        recylerview.addItemDecoration(
            DividerItemDecoration(
                recylerview.context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = SurahListRCAdapter(surahListViewModel.surahListLiveSurahDataClassName.value!!)
        recylerview.adapter = adapter
        adapter.surahListLiveClickedPosition.observe(viewLifecycleOwner, Observer { position ->
            val bundle = bundleOf("surahIndex" to position)
            navigation.navigate(R.id.navigation_surah_signle, bundle)
        })
    }
}