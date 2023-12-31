package com.animehub.otakuvortex.presentation.adapters.favorites

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.animehub.otakuvortex.R
import com.animehub.otakuvortex.data.local.model.AnimeContent
import com.animehub.otakuvortex.presentation.ui.favorite.FavoriteFragmentViewModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FavAnimeRecyclerViewAdapter(
    private val favViewModel: FavoriteFragmentViewModel
): RecyclerView.Adapter<FavAnimeRecyclerViewAdapter.FavAnimeViewHolder>() {

    private lateinit var sp: SharedPreferences

    inner class FavAnimeViewHolder(item: View): RecyclerView.ViewHolder(item){
        val tvImage = item.findViewById<ImageButton>(R.id.tvFavImage)
        val etTitle = item.findViewById<TextView>(R.id.etFavName)
        val deleteFromFav = item.findViewById<FloatingActionButton>(R.id.btnDeleteFavorite)
    }

    private val differCallback = object: DiffUtil.ItemCallback<AnimeContent>(){
        override fun areItemsTheSame(oldItem: AnimeContent, newItem: AnimeContent): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: AnimeContent, newItem: AnimeContent): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAnimeViewHolder {
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return FavAnimeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.fav_content_cardview,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavAnimeViewHolder, position: Int) {
        val indexElement = differ.currentList[position]
        holder.etTitle.text = indexElement.title
        Glide.with(holder.itemView.context)
            .load(indexElement.imageUrl)
            .into(holder.tvImage)
        holder.tvImage.setOnClickListener{
            val editor = sp.edit()
            editor.apply {
                putString("animeId", indexElement.id.toString())
            }
            editor.apply()
            it.findNavController().navigate(
                R.id.action_favoriteFragment_to_infoFragment
            )
        }
        holder.deleteFromFav.let {
            it.setOnClickListener{
                favViewModel.deleteFavoriteAnimeFromDB(
                    AnimeContent(
                        id = indexElement.id,
                        title = indexElement.title,
                        imageUrl = indexElement.imageUrl
                    )
                )
                notifyItemRemoved(position)
            }
        }
    }
}