package com.animehub.otakuvortex.paging.manga.searched

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.domain.modal.mamga.searchmanga.SearchedMangaData
import com.bumptech.glide.Glide

class SearchedMangaPagingAdaptor: PagingDataAdapter<SearchedMangaData, SearchedMangaPagingAdaptor.SearchedMangaViewHolder>(COPARATOR) {

    inner class SearchedMangaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val searchedImage = itemView.findViewById<ImageView>(R.id.tvSearchedImage)
        val searchedTitle = itemView.findViewById<TextView>(R.id.etSearchedName)
    }

    companion object {
        private val COPARATOR = object: DiffUtil.ItemCallback<SearchedMangaData>(){
            override fun areItemsTheSame(
                oldItem: SearchedMangaData,
                newItem: SearchedMangaData
            ): Boolean {
                return oldItem.animeId == newItem.animeId
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedMangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searched_cardview, parent, false)
        return SearchedMangaViewHolder(view)
    }
}