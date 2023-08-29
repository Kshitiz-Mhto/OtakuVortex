package com.animehub.otakuvortex.paging.anime

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.domain.modal.anime.topanime.TopAnimeData
import com.bumptech.glide.Glide

class TopAnimePagingAdaptor: PagingDataAdapter<TopAnimeData, TopAnimePagingAdaptor.TopAnimeViewHolder>(COMPARATOR) {

    private lateinit var sp: SharedPreferences
    class TopAnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val animeImage = itemView.findViewById<ImageView>(R.id.tvAnimeImage)
    }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<TopAnimeData>(){
            override fun areItemsTheSame(oldItem: TopAnimeData, newItem: TopAnimeData): Boolean {
                return oldItem.animeId == newItem.animeId
            }

            override fun areContentsTheSame(oldItem: TopAnimeData, newItem: TopAnimeData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: TopAnimeViewHolder, position: Int) {
        val index_element = getItem(position)
        Glide.with(holder.itemView.context)
            .load(index_element!!.imageUrl)
            .into(holder.animeImage)
        holder.animeImage.setOnClickListener{
            val editor = sp.edit()
            editor.apply{
                putString("animeId", index_element.animeId.toString())
            }
            editor.apply()
            it.findNavController().navigate(
                R.id.action_homeFragment_to_infoFragment
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_anime_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return TopAnimeViewHolder(view)
    }

}