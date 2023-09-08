package com.animehub.otakuvortex.paging.anime.searched

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.data.local.model.AnimeContent
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData
import com.animehub.otakuvortex.presentation.ui.favorite.FavoriteFragmentViewModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchedAnimePagingAdaptor(
    private val viewModel: FavoriteFragmentViewModel
): PagingDataAdapter<SearchedAnimeData, SearchedAnimePagingAdaptor.SearchedAnimeViewHolder>(
    COMPARATOR
) {
    private lateinit var sp: SharedPreferences

   class SearchedAnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val searchedImage = itemView.findViewById<ImageView>(R.id.tvSearchedImage)
       val searchedTitle = itemView.findViewById<TextView>(R.id.etSearchedName)
       val btnSave = itemView.findViewById<FloatingActionButton>(R.id.btnSaveToFavoriteSearched)
   }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<SearchedAnimeData>(){
            override fun areItemsTheSame(oldItem: SearchedAnimeData, newItem: SearchedAnimeData): Boolean {
                return oldItem.animeId == newItem.animeId
            }

            override fun areContentsTheSame(oldItem: SearchedAnimeData, newItem: SearchedAnimeData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: SearchedAnimeViewHolder, position: Int) {
        val indexElement = getItem(position)
        Glide.with(holder.itemView.context)
            .load(indexElement!!.imageUrl)
            .into(holder.searchedImage)
        holder.searchedTitle.text = indexElement.title
        holder.searchedImage.setOnClickListener {
            val editor = sp.edit()
            editor.apply {
                putString("animeId", indexElement.animeId.toString())
            }
            editor.apply()
            it.findNavController().navigate(
                R.id.action_searchmeFragment_to_infoFragment
            )
        }
        holder.btnSave.let {
            it.setOnClickListener {
                viewModel._savedAnimeLiveData.postValue(
                    AnimeContent(
                        indexElement.animeId,
                        indexElement.title,
                        indexElement.imageUrl
                    )
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedAnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searched_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return SearchedAnimeViewHolder(view)
    }

}