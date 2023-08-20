package com.animehub.otakuvortex.presentation.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.databinding.FragmentHomeBinding
import com.animehub.otakuvortex.paging.anime.TopAnimePagingAdaptor
import com.animehub.otakuvortex.presentation.ui.home.anime.topanime.TopAnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
//    lateinit var topAnimeViewModel: TopAnimeViewModel
    private val topAnimeViewModel: TopAnimeViewModel by viewModels()
    lateinit var topAnimeRecyclerView: RecyclerView
    lateinit var adaptor: TopAnimePagingAdaptor

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        topAnimeRecyclerView = binding.topAnimeRecyclerview
        topAnimeRecyclerView.setBackgroundColor(Color.TRANSPARENT)
        topAnimeRecyclerView.layoutManager  =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adaptor = TopAnimePagingAdaptor()
        topAnimeRecyclerView.adapter = adaptor

        showTopAnimeList()

        return binding.root
    }

    private fun showTopAnimeList() {

        viewLifecycleOwner.lifecycleScope.launch {
            topAnimeViewModel.topAnimeListValue.collect { state ->
                if (state.isLoading) {
                    Log.i("UI load", "lol")
                }
                else if(state.error.isNotEmpty()){
                    Log.i("UI error", "lol")
                }else if (state.topAnimeList != null) {
                    val topAnimeList = state.topAnimeList
                    adaptor.submitData(lifecycle, topAnimeList)
                }
            }
        }

    }

}