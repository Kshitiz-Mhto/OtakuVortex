package com.animehub.otakuvortex.paging.manga

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
import com.animehub.otakuvortex.data.local.model.MangaContent
import com.animehub.otakuvortex.domain.modal.mamga.topmanga.TopMangaData
import com.animehub.otakuvortex.presentation.ui.favorite.FavoriteFragmentViewModel
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TopMangaPadingAdaptor(
    private val viewModel: FavoriteFragmentViewModel
) : PagingDataAdapter<TopMangaData, TopMangaPadingAdaptor.TopMangaViewHolder>(COMPARATOR) {

    private lateinit var sp: SharedPreferences

    class TopMangaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mangaImage = itemView.findViewById<ImageView>(R.id.tvMangaImage)
        val btnSave = itemView.findViewById<FloatingActionButton>(R.id.btnSaveToFavoriteManga)
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
            .into(holder.mangaImage)
        holder.mangaImage.setOnClickListener{
            val editor = sp.edit()
            editor.putString("mangaId", index_element.mangaId.toString())
            editor.apply()
            it.findNavController().navigate(
                R.id.action_homeFragment_to_infoFragment
            )
        }
        holder.btnSave.let {
            it.setOnClickListener {
                viewModel._savedMangaLiveData.postValue(
                    MangaContent(
                        index_element.mangaId,
                        index_element.title,
                        index_element.imageUrl
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMangaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_manga_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return TopMangaViewHolder(view)
    }

}