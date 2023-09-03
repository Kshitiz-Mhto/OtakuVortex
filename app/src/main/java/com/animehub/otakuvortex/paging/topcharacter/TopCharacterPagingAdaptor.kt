package com.animehub.otakuvortex.paging.topcharacter

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
import com.animehub.otakuvortex.domain.modal.topcharacter.TopCharacterModel
import com.bumptech.glide.Glide

class TopCharacterPagingAdaptor: PagingDataAdapter<TopCharacterModel, TopCharacterPagingAdaptor.TopCharacterViewHolder>(COMPARATOR) {

    private lateinit var sp: SharedPreferences
    class TopCharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val characterImage: ImageView = itemView.findViewById<ImageView>(R.id.tvCharImage)
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
            .into(holder.characterImage)
        holder.characterImage.setOnClickListener{
            val editor = sp.edit()
            editor.apply{
                putString("characterId", index_element.charId.toString())
            }
            editor.apply()
            it.findNavController().navigate(
                R.id.action_homeFragment_to_characterInfoFragment
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_char_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return TopCharacterViewHolder(view)
    }

}