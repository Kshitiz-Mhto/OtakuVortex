package com.animehub.otakuvortex.paging.manga.searched

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
import com.animehub.otakuvortex.data.local.model.MangaContent
import com.animehub.otakuvortex.domain.modal.mamga.searchmanga.SearchedMangaData
import com.animehub.otakuvortex.presentation.ui.favorite.FavoriteFragmentViewModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchedMangaPagingAdaptor(
    private val viewModel: FavoriteFragmentViewModel
): PagingDataAdapter<SearchedMangaData, SearchedMangaPagingAdaptor.SearchedMangaViewHolder>(COPARATOR) {

    private lateinit var sp: SharedPreferences

    inner class SearchedMangaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val searchedImage = itemView.findViewById<ImageView>(R.id.tvSearchedImage)
        val searchedTitle = itemView.findViewById<TextView>(R.id.etSearchedName)
        val btnSave = itemView.findViewById<FloatingActionButton>(R.id.btnSaveToFavoriteSearched)
    }

    companion object {
        private val COPARATOR = object: DiffUtil.ItemCallback<SearchedMangaData>(){
            override fun areItemsTheSame(
                oldItem: SearchedMangaData,
                newItem: SearchedMangaData
            ): Boolean {
                return oldItem.mangaId== newItem.mangaId
            }

            override fun areContentsTheSame(
                oldItem: SearchedMangaData,
                newItem: SearchedMangaData
            ): Boolean {
                return  oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: SearchedMangaViewHolder, position: Int) {
        val indexElement = getItem(position)
        Glide.with(holder.itemView.context)
            .load(indexElement!!.imageUrl)
            .into(holder.searchedImage)
        holder.searchedTitle.text = indexElement.title
        holder.searchedImage.setOnClickListener{
            val editor = sp.edit()
            editor.putString("mangaId", indexElement.mangaId.toString())
            editor.apply()
            it.findNavController().navigate(
                R.id.action_searchmeFragment_to_infoFragment
            )
        }
        holder.btnSave.let {
            it.setOnClickListener {
                viewModel._savedMangaLiveData.postValue(
                    MangaContent(
                        indexElement.mangaId,
                        indexElement.title,
                        indexElement.imageUrl
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedMangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searched_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return SearchedMangaViewHolder(view)
    }
}