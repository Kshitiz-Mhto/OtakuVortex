package com.animehub.otakuvortex.presentation.ui.search

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.databinding.FragmentSearchmeBinding
import com.animehub.otakuvortex.paging.anime.searched.SearchedAnimePagingAdaptor
import com.animehub.otakuvortex.paging.loaderadapter.LoaderAdaptor
import com.animehub.otakuvortex.paging.manga.searched.SearchedMangaPagingAdaptor
import com.animehub.otakuvortex.presentation.ui.favorite.FavoriteFragmentViewModel
import com.animehub.otakuvortex.presentation.ui.search.searchanime.SearchedAnimeViewModel
import com.animehub.otakuvortex.presentation.ui.search.searchmanga.SearchedMangaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class SearchmeFragment : Fragment() {

    private lateinit var binding: FragmentSearchmeBinding

    private lateinit var searchedAnimeRecyclerview: RecyclerView
    private lateinit var searchedMangaRecyclerView: RecyclerView
    private val searchedAnimeViewModel: SearchedAnimeViewModel by viewModels()
    private val searchedMangaViewModel: SearchedMangaViewModel by viewModels()
    private val favoriteViewModel: FavoriteFragmentViewModel by viewModels()
    private lateinit var searchedAnimePagingAdaptor: SearchedAnimePagingAdaptor
    private lateinit var searchedMangaPagingAdaptor: SearchedMangaPagingAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchmeBinding.inflate(inflater, container, false)

        searchedAnimeRecyclerview = binding.searchedAnimeRecylervew
        searchedMangaRecyclerView = binding.searchedMangaRecylervew
        searchedMangaRecyclerView.setBackgroundColor(Color.TRANSPARENT)
        searchedAnimeRecyclerview.setBackgroundColor(Color.TRANSPARENT)
        searchedMangaRecyclerView.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.HORIZONTAL, false)
        searchedAnimeRecyclerview.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.HORIZONTAL, false)
        searchedAnimePagingAdaptor = SearchedAnimePagingAdaptor(favoriteViewModel)
        searchedMangaPagingAdaptor = SearchedMangaPagingAdaptor(favoriteViewModel)

        binding.searchResult.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchText = query!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty() or searchText.isNotBlank()){
                    viewLifecycleOwner.lifecycleScope.launch {
                        binding.linearLayoutSearchedAnime.visibility = View.VISIBLE
                        searchedAnimeViewModel.getSearchedAnimeList(searchText)
                        searchedAnimeViewModel.searchedAnimeListValue.collect{ state ->
                            when{
                                state.isLoading -> {
                                    Log.i("UI load", "lol")
                                }
                                state.error.isNotEmpty() -> {
                                    Log.i("UI error", "lol")
                                }
                                else -> {
                                    val animeList = state.searchedAnimeList
                                    if(animeList != null) {
                                        searchedAnimePagingAdaptor.submitData(
                                            lifecycle,
                                            animeList
                                        )
                                    }
                                }
                            }
                        }
                    }
                    viewLifecycleOwner.lifecycleScope.launch {
                        binding.linearLayoutSearchedManaga.visibility = View.VISIBLE
                        searchedMangaViewModel.getSearchedMangaList(searchText)
                        searchedMangaViewModel.searchedMangaListValue.collect{ stateo ->
                            when{
                                stateo.isLoading -> {
                                    Log.i("UI load", "lol")
                                }
                                stateo.error.isNotEmpty() -> {
                                    Log.i("UI error", "lol")
                                }
                                else -> {
                                    val mangaList = stateo.searchedMangaList
                                    if(mangaList != null) {
                                        searchedMangaPagingAdaptor.submitData(
                                            lifecycle,
                                            mangaList
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchedAnimeRecyclerview.adapter = searchedAnimePagingAdaptor.withLoadStateHeaderAndFooter(
            header = LoaderAdaptor(),
            footer = LoaderAdaptor()
        )

        searchedMangaRecyclerView.adapter = searchedMangaPagingAdaptor.withLoadStateHeaderAndFooter(
            header = LoaderAdaptor(),
            footer = LoaderAdaptor()
        )

        favoriteViewModel.savedAnimeLiveData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                favoriteViewModel.insertFavoriteAnimeToDB(it)
            }
        }

        favoriteViewModel.savedMangaLiveData.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch {
                favoriteViewModel.insertFavoriteMangaToDB(it)
            }
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        binding.linearLayoutSearchedManaga.visibility = View.GONE
        binding.linearLayoutSearchedAnime.visibility = View.GONE
    }

}