package com.onboarding.marvel_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.marvel_mvvm.R
import com.onboarding.marvel_mvvm.databinding.ItemCharacterBinding
import com.onboarding.marvel_mvvm.listener.CharacterClickListener

class CharacterAdapter(private val characterItems: List<MarvelCharacter>, private val characterClickListener: CharacterClickListener) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false), characterClickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterItems[position])
    }

    override fun getItemCount() = characterItems.size

    class ViewHolder(itemView: View, private val characterClickListener: CharacterClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: MarvelCharacter) {
            with(ItemCharacterBinding.bind(itemView)) {
                textViewItemCharacterName.text = character.name
                Glide.with(itemView.context)
                    .load(
                        String.format(
                            URL_FORMAT,
                            character.thumbnail.path,
                            character.thumbnail.extension
                        )
                    )
                    .into(imageViewItemCharacterThumbnail)
                itemView.setOnClickListener {
                    characterClickListener.onCharacterClick(character)
                }
            }
        }
    }

    companion object {
        private const val URL_FORMAT = "%s/portrait_medium.%s"
    }
}
