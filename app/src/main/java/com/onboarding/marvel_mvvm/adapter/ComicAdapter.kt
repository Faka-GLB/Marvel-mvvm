package com.onboarding.marvel_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onboarding.domain.entity.CollectionItem
import com.onboarding.marvel_mvvm.R
import com.onboarding.marvel_mvvm.databinding.ItemComicNameBinding

class ComicAdapter(private val comicItems: List<CollectionItem>) : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comic_name, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comicItems[position])
    }

    override fun getItemCount() = comicItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: CollectionItem) {
            with(ItemComicNameBinding.bind(itemView)) {
                textViewItemCharacterComicName.text = comic.name
            }
        }
    }
}
