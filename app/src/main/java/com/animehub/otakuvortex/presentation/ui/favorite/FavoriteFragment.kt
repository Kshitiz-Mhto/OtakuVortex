package com.animehub.otakuvortex.presentation.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.databinding.FragmentFavoriteBinding
import com.animehub.otakuvortex.presentation.adapters.favorites.FavAnimeRecyclerViewAdapter
import com.animehub.otakuvortex.presentation.adapters.favorites.FavCharacterRecyclerViewAdapter
import com.animehub.otakuvortex.presentation.adapters.favorites.FavMangaRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favViewModel: FavoriteFragmentViewModel by viewModels()

    private lateinit var favAnimeRecyclerView: RecyclerView
    private lateinit var favMangaRecyclerView: RecyclerView
    private lateinit var favCharacterRecyclerView: RecyclerView

    private lateinit var favAnimeAdapter: FavAnimeRecyclerViewAdapter
    private lateinit var favMangaAdapter: FavMangaRecyclerViewAdapter
    private lateinit var favCharacterAdapter: FavCharacterRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        favAnimeRecyclerView = binding.favoriteAnimeContentList
        favMangaRecyclerView = binding.favoriteMangaContentList
        favCharacterRecyclerView = binding.favoriteCharacterContentList

        favAnimeAdapter = FavAnimeRecyclerViewAdapter(favViewModel)
        favMangaAdapter = FavMangaRecyclerViewAdapter(favViewModel)
        favCharacterAdapter = FavCharacterRecyclerViewAdapter(favViewModel)

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.getFavoriteAnimeContentList()
            favViewModel.favoriteAnimeListValue.observe(viewLifecycleOwner){
                it.let {
                    binding.etFavAnime.visibility = View.VISIBLE
                    favAnimeAdapter.differ.submitList(it)
                    favAnimeRecyclerView.adapter = favAnimeAdapter
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.getFavoriteMangaContentList()
            favViewModel.favoriteMangaListValue.observe(viewLifecycleOwner) {
                it.let {
                    binding.etFavManga.visibility = View.VISIBLE
                    favMangaAdapter.differ.submitList(it)
                    favMangaRecyclerView.adapter = favMangaAdapter
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.getFavoriteCharacterContentList()
            favViewModel.favoriteCharacterListValue.observe(viewLifecycleOwner) {
                it.let {
                    binding.etFavCharacter.visibility = View.VISIBLE
                    favCharacterAdapter.differ.submitList(it)
                    favCharacterRecyclerView.adapter = favCharacterAdapter
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.deleteSuccessfullResponseForAnime.observe(viewLifecycleOwner){
                if (it){
                    Toast.makeText(requireContext(), "Deleted Successfully \uD83D\uDE1E", Toast.LENGTH_LONG).show()
                    favViewModel.deleteSuccessfullResponseForAnime.value = false
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.deleteSuccessfullResponseForManga.observe(viewLifecycleOwner){
                if (it){
                    Toast.makeText(requireContext(), "Deleted Successfully \uD83D\uDE1E", Toast.LENGTH_LONG).show()
                    favViewModel.deleteSuccessfullResponseForManga.value = false
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favViewModel.deleteSuccessfullResponseForCharacter.observe(viewLifecycleOwner){
                if (it){
                    Toast.makeText(requireContext(), "Deleted Successfully \uD83D\uDE1E", Toast.LENGTH_LONG).show()
                    favViewModel.deleteSuccessfullResponseForCharacter.value = false
                }
            }
        }

        return binding.root
    }
}