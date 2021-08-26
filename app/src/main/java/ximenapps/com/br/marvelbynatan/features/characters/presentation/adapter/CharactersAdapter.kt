package ximenapps.com.br.marvelbynatan.features.characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character

class CharactersAdapter : ListAdapter<Character, CharacterItemViewHolder>(CharactersDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharacterItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

private class CharactersDiff : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}