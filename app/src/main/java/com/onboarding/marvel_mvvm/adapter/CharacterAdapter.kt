package com.onboarding.marvel_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.marvel_mvvm.R
import com.onboarding.marvel_mvvm.databinding.ItemCharacterBinding

class CharacterAdapter(private val characterItems: List<MarvelCharacter>):  RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterItems[position])
    }

    override fun getItemCount(): Int {
        return characterItems.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(character: MarvelCharacter) {
            with(ItemCharacterBinding.bind(itemView)) {
                textViewItemCharacterName.text = character.name
            }
        }
    }
}
