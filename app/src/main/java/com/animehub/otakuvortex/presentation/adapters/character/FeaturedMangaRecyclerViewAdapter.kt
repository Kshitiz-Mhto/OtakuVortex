package com.animehub.otakuvortex.presentation.adapters.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.data.remote.dto.characterbyid.Manga

class FeaturedMangaRecyclerViewAdapter: ListAdapter<Manga, FeaturedMangaRecyclerViewAdapter.FeaturedMangaViewHolder>(DiffUtily()) {

    inner class FeaturedMangaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val featuredManga = view.findViewById<Button>(R.id.btnInfoFeatured)

        fun bind(item: Manga){
            featuredManga.text = item.manga.title
        }
    }

    class DiffUtily: DiffUtil.ItemCallback<Manga>(){
        override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem.role == newItem.role
        }

        override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedMangaViewHolder {
        return FeaturedMangaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.featured_program,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: FeaturedMangaViewHolder, position: Int) {
        val indexElement = getItem(position)
        holder.bind(indexElement)
    }

}