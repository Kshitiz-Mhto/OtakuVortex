package com.animehub.otakuvortex.presentation.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.databinding.FragmentHomeBinding
import com.animehub.otakuvortex.paging.anime.TopAnimePagingAdaptor
import com.animehub.otakuvortex.paging.loaderadapter.LoaderAdaptor
import com.animehub.otakuvortex.paging.manga.TopMangaPadingAdaptor
import com.animehub.otakuvortex.paging.topcharacter.TopCharacterPagingAdaptor
import com.animehub.otakuvortex.presentation.ui.home.anime.topanime.TopAnimeViewModel
import com.animehub.otakuvortex.presentation.ui.home.manga.topmanga.TopMangaViewModel
import com.animehub.otakuvortex.presentation.ui.home.topcharacter.TopCharacterViewModel
import com.animehub.otakuvortex.presentation.ui.search.SearchmeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

//    lateinit var topAnimeViewModel: TopAnimeViewModel
    private val topAnimeViewModel: TopAnimeViewModel by viewModels()
    private val topMangaViewModel: TopMangaViewModel by viewModels()
    private val topCharacterViewModel: TopCharacterViewModel by viewModels()

    lateinit var topAnimeRecyclerView: RecyclerView
    lateinit var topMangaRecyclerView: RecyclerView
    lateinit var topCharacterRecyclerView: RecyclerView

    lateinit var topAnimeAdaptor: TopAnimePagingAdaptor
    lateinit var topMangaAdaptor: TopMangaPadingAdaptor
    lateinit var topCharacterAdaptor: TopCharacterPagingAdaptor

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
        topAnimeRecyclerView.layoutManager  = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topAnimeAdaptor = TopAnimePagingAdaptor()
        topAnimeRecyclerView.adapter = topAnimeAdaptor.withLoadStateHeaderAndFooter(
            header = LoaderAdaptor(),
            footer = LoaderAdaptor()
        )

        topMangaRecyclerView = binding.topMangaRecyclerview
        topMangaRecyclerView.setBackgroundColor(Color.TRANSPARENT)
        topMangaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topMangaAdaptor = TopMangaPadingAdaptor()
        topMangaRecyclerView.adapter = topMangaAdaptor.withLoadStateHeaderAndFooter(
            header = LoaderAdaptor(),
            footer = LoaderAdaptor()
        )

        topCharacterRecyclerView = binding.topCharacterRecyclerview
        topCharacterRecyclerView.setBackgroundColor(Color.TRANSPARENT)
        topCharacterRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topCharacterAdaptor = TopCharacterPagingAdaptor()
        topCharacterRecyclerView.adapter = topCharacterAdaptor.withLoadStateHeaderAndFooter(
            header = LoaderAdaptor(),
            footer = LoaderAdaptor()
        )

        showTopAnimeList()
        showTopMangaList()
        showTopCharacterList()

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
                    topAnimeAdaptor.submitData(lifecycle, topAnimeList)
                }
            }
        }
    }

    private fun showTopMangaList(){
        viewLifecycleOwner.lifecycleScope.launch {
            topMangaViewModel.topMangaListValue.collect{ state ->
                if (state.isLoading) {
                    Log.i("UI load", "lol")
                }
                else if(state.error.isNotEmpty()){
                    Log.i("UI error", "lol")
                }else if (state.topMangaList != null) {
                    val topMangaList = state.topMangaList
                    topMangaAdaptor.submitData(lifecycle, topMangaList)
                }
            }
        }
    }

    private fun showTopCharacterList(){
        viewLifecycleOwner.lifecycleScope.launch {
            topCharacterViewModel.topCharacterListValue.collect{ state ->
                if (state.isLoading) {
                    Log.i("UI load", "lol")
                }
                else if(state.error.isNotEmpty()){
                    Log.i("UI error", "lol")
                }else if (state.topCharacterList != null) {
                    val topCharacterList = state.topCharacterList
                    topCharacterAdaptor.submitData(lifecycle, topCharacterList)
                }
            }
        }
    }

}
