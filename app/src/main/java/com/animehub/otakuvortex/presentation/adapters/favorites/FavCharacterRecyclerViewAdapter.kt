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
import com.animehub.otakuvortex.data.local.model.CharacterContent
import com.bumptech.glide.Glide

class FavCharacterRecyclerViewAdapter: RecyclerView.Adapter<FavCharacterRecyclerViewAdapter.FavCharacterViewHolder>() {

    private lateinit var sp: SharedPreferences

    inner class FavCharacterViewHolder(item: View): RecyclerView.ViewHolder(item){
        val tvImage = item.findViewById<ImageButton>(R.id.tvFavImage)
        val etTitle = item.findViewById<TextView>(R.id.etFavName)
    }

    private val differCallback = object: DiffUtil.ItemCallback<CharacterContent>(){
        override fun areItemsTheSame(oldItem: CharacterContent, newItem: CharacterContent): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterContent, newItem: CharacterContent): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCharacterViewHolder {
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return FavCharacterViewHolder(
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

    override fun onBindViewHolder(holder: FavCharacterViewHolder, position: Int) {
        val indexElement = differ.currentList[position]
        holder.etTitle.text = indexElement.title
        Glide.with(holder.itemView.context)
            .load(indexElement.imageUrl)
            .into(holder.tvImage)
        holder.tvImage.setOnClickListener{
            val editor = sp.edit()
            editor.apply {
                putString("characterId", indexElement.id.toString())
            }
            editor.apply()
            it.findNavController().navigate(
                R.id.action_favoriteFragment_to_characterInfoFragment
            )
        }
    }
}