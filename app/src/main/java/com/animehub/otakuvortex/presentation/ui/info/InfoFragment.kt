package com.animehub.otakuvortex.presentation.ui.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.animehub.otakuvortex.databinding.FragmentInfoBinding
import com.animehub.otakuvortex.presentation.ui.info.anime.AnimeByIdViewModel
import com.animehub.otakuvortex.presentation.ui.info.character.CharacterByIdViewModel
import com.animehub.otakuvortex.presentation.ui.info.manga.MangaByIdViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val animeByIdViewModel: AnimeByIdViewModel by viewModels()
    private val mangaByIdViewModel: MangaByIdViewModel by viewModels()
    private val characterByIdViewModel: CharacterByIdViewModel by viewModels()

    private lateinit var sp: SharedPreferences
    private lateinit var animeId: String
    private lateinit var mangaId: String
    private lateinit var characterId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        animeId = sp.getString("animeId", "0") ?: "0"
        mangaId = sp.getString("mangaId", "1") ?: "1"
        characterId = sp.getString("characterId", "2") ?: "2"

        if (animeId != "0") {
            showAnimeDetails()
            animeId = "0"
            sp.edit().putString("animeId", "0").apply()
        }
        if (mangaId != "1") {
            showMangaDetails()
            mangaId = "1"
            sp.edit().putString("mangaId", "1").apply()
        }
        if(characterId != "2"){
            showCharacterDetails()
            characterId = "2"
            sp.edit().putString("characterId", "2").apply()
        }

        return binding.root
    }

    private fun showAnimeDetails(){
        viewLifecycleOwner.lifecycleScope.launch {
            animeByIdViewModel.getAnimeById(animeId.toInt())
            animeByIdViewModel.animeByIdListValue.collect{state ->
                when{
                    state.isLoading -> Log.i("UI load", "lol")
                    state.error.isNotEmpty() -> Log.i("UI error", "lol")
                    else -> {
                        val animeDetail = state.animeById
                        if(animeDetail != null) {
                            Glide.with(requireContext())
                                .load(animeDetail.imageUrl)
                                .into(binding.tvInfoImgage)
                        }
                    }
                }
            }
        }
    }

    private fun showMangaDetails(){
        viewLifecycleOwner.lifecycleScope.launch {
            mangaByIdViewModel.getMangaById(mangaId.toInt())
            mangaByIdViewModel.mangaByIdListValue.collect{state ->
                when{
                    state.isLoading -> Log.i("UI load", "lol")
                    state.error.isNotEmpty() -> Log.i("UI error", "lol")
                    else ->{
                        val mangaDetail = state.mangaById
                        if(mangaDetail!=null){
                            Glide.with(requireContext())
                                .load(mangaDetail.imageUrl)
                                .into(binding.tvInfoImgage)
                        }
                    }
                }
            }
        }
    }

    private fun showCharacterDetails(){
        viewLifecycleOwner.lifecycleScope.launch {
            characterByIdViewModel.getCharacterById(characterId.toInt())
            characterByIdViewModel.characterByIdListValue.collect{state ->
                when{
                    state.isLoading -> Log.i("UI load", "lol")
                    state.error.isNotEmpty() -> Log.i("UI error", "lol")
                    else ->{
                        val characterDetail = state.chracterById
                        if(characterDetail!=null){
                            Glide.with(requireContext())
                                .load(characterDetail.imageUrl)
                                .into(binding.tvInfoImgage)
                        }
                    }
                }
            }
        }
    }

}