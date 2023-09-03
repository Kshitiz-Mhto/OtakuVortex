package com.animehub.otakuvortex

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
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.databinding.FragmentCharacterInfoBinding
import com.animehub.otakuvortex.presentation.adapters.character.FeaturedAnimeRecyclerViewAdapter
import com.animehub.otakuvortex.presentation.adapters.character.FeaturedMangaRecyclerViewAdapter
import com.animehub.otakuvortex.presentation.ui.info.character.CharacterByIdViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterInfoFragment : Fragment() {

    private lateinit var binding: FragmentCharacterInfoBinding
    private val characterByIdViewModel: CharacterByIdViewModel by viewModels()
    private lateinit var sp: SharedPreferences
    private lateinit var characterId: String
    private lateinit var featuredMangaRecyclerView: RecyclerView
    private lateinit var featuredAnimeRecyclerView: RecyclerView
    private lateinit var featuredMangaAdapter: FeaturedMangaRecyclerViewAdapter
    private lateinit var featuredAnimeAdapter: FeaturedAnimeRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)
        sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        featuredAnimeRecyclerView = binding.featuredAnimeRecyclerView
        featuredMangaRecyclerView = binding.featuredMangaRecyclerView

        featuredAnimeAdapter = FeaturedAnimeRecyclerViewAdapter()
        featuredMangaAdapter = FeaturedMangaRecyclerViewAdapter()

        characterId = sp.getString("characterId", "0") ?: "0"
        showCharacterDetails()

        return binding.root
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
                                .into(binding.tvInfoCharacterImgage)
                            binding.infoTitle.text = characterDetail.title
                            binding.infoDescription.text = characterDetail.about
                            binding.ratingValue.text =  "⭐ "+ characterDetail.favorites
                            if (characterDetail.titleKanji.isNotEmpty()) {
                                binding.infoTitleAlias.visibility = View.VISIBLE
                                binding.infoTitleAlias.text = "As:  " + characterDetail.titleKanji
                            }
                            featuredMangaAdapter.submitList(characterDetail.featuredManga)
                            featuredAnimeAdapter.submitList(characterDetail.featuredAnime)

                            featuredAnimeRecyclerView.adapter = featuredAnimeAdapter
                            featuredMangaRecyclerView.adapter = featuredMangaAdapter
                        }
                    }
                }
            }
        }
    }

}