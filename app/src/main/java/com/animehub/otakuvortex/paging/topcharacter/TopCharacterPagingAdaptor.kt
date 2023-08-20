package com.animehub.otakuvortex.paging.topcharacter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import com.bumptech.glide.Glide

class TopCharacterPagingAdaptor: PagingDataAdapter<TopCharacterModel, TopCharacterPagingAdaptor.TopCharacterViewHolder>(COMPARATOR) {

    class TopCharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val animeImage = itemView.findViewById<ImageView>(R.id.tvAnimeImage)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<TopCharacterModel>(){
            override fun areItemsTheSame(
                oldItem: TopCharacterModel,
                newItem: TopCharacterModel
            ): Boolean {
                return oldItem.charId == newItem.charId
            }

            override fun areContentsTheSame(
                oldItem: TopCharacterModel,
                newItem: TopCharacterModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: TopCharacterViewHolder, position: Int) {
        val index_element = getItem(position)
        Glide.with(holder.itemView.context)
            .load(index_element!!.imageUrl)
            .into(holder.animeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_anime_cardview, parent, false)
        return TopCharacterViewHolder(view)
    }

}