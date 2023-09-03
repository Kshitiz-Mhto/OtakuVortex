package com.animehub.otakuvortex.presentation.adapters.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.data.remote.dto.characterbyid.Anime

class FeaturedAnimeRecyclerViewAdapter: ListAdapter<Anime, FeaturedAnimeRecyclerViewAdapter.FeaturedAnimeViewHolder>(DiffUtily()) {

    inner class FeaturedAnimeViewHolder(view: View): RecyclerView.ViewHolder(view){
        val featuredAnime = view.findViewById<Button>(R.id.btnInfoFeatured)

        fun bind(item: Anime){
            featuredAnime.text = item.anime.title
        }
    }

    class DiffUtily: DiffUtil.ItemCallback<Anime>(){
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.role == newItem.role
        }

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedAnimeViewHolder {
        return FeaturedAnimeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.featured_program,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: FeaturedAnimeViewHolder, position: Int) {
        val indexElement = getItem(position)
        holder.bind(indexElement)
    }

}