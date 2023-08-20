package com.animehub.otakuvortex.paging.manga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.bumptech.glide.Glide

class TopMangaPadingAdaptor: PagingDataAdapter<TopMangaData, TopMangaPadingAdaptor.TopMangaViewHolder>(COMPARATOR) {

    class TopMangaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val animeImage = itemView.findViewById<ImageView>(R.id.tvAnimeImage)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<TopMangaData>(){
            override fun areItemsTheSame(oldItem: TopMangaData, newItem: TopMangaData): Boolean {
                return oldItem.mangaId == newItem.mangaId
            }

            override fun areContentsTheSame(oldItem: TopMangaData, newItem: TopMangaData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: TopMangaViewHolder, position: Int) {
        val index_element = getItem(position)
        Glide.with(holder.itemView.context)
            .load(index_element!!.imageUrl)
            .into(holder.animeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_anime_cardview, parent, false)
        return TopMangaViewHolder(view)
    }

}