package com.animehub.otakuvortex.presentation.adapters.manga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.data.remote.dto.manga.mangabyid.Genre

class MangaGenreRecyclerViewAdapter: ListAdapter<Genre, MangaGenreRecyclerViewAdapter.GenreViewHolder>(DiffUtily()) {

    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreTitle = view.findViewById<Button>(R.id.btnInfoGenre)

        fun bind(item: Genre) {
            genreTitle.text = item.name
        }
    }

    class DiffUtily : androidx.recyclerview.widget.DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.genre_layout,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val indexElement = getItem(position)
        holder.bind(indexElement)
    }
}


