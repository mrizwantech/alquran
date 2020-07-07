package com.rizwantech.alquran.ui.ayas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rizwantech.alquran.R
import com.rizwantech.alquran.ui.suran_list.SurahListRCAdapter
import com.rizwantech.alquran.ui.suran_list.SurahListViewModel
import kotlinx.android.synthetic.main.fragment_surah.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AyasFragment : Fragment() {
    private lateinit var surahListViewModel: SurahListViewModel
    private lateinit var recylerview: RecyclerView
private lateinit var adapter: AyasRCAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        surahListViewModel =
            ViewModelProvider(this).get(SurahListViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_surah, container, false)
        recylerview = view.findViewById(R.id.rc_ayas)
        recylerview.setHasFixedSize(true)
        val bundle = arguments
        val position = bundle?.getInt("surahIndex")
        CoroutineScope(Dispatchers.Main).launch {
            position?.let { surahListViewModel.getSurahs(it) }
        }
        surahListViewModel.liveDataAyahsItem.observe(viewLifecycleOwner, Observer { ayas->
            val layoutManager = LinearLayoutManager(context)
            recylerview.layoutManager = layoutManager
            recylerview.addItemDecoration(
                DividerItemDecoration(
                    recylerview.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = AyasRCAdapter(ayas)
            recylerview.adapter = adapter
        })
        return view
    }

}
