package com.animehub.otakuvortex.paging.anime.searched

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.domain.modal.anime.searchanime.SearchedAnimeData
import com.bumptech.glide.Glide

class SearchedAnimePagingAdaptor: PagingDataAdapter<SearchedAnimeData, SearchedAnimePagingAdaptor.SearchedAnimeViewHolder>(
    COMPARATOR
) {

   class SearchedAnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val searchedImage = itemView.findViewById<ImageView>(R.id.tvSearchedImage)
       val searchedTitle = itemView.findViewById<TextView>(R.id.etSearchedName)
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedAnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searched_cardview, parent, false)
        return SearchedAnimeViewHolder(view)
    }

}