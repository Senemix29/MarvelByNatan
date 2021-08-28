package ximenapps.com.br.marvelbynatan.features.characters.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.databinding.ItemCharacterBinding
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character

class CharacterItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding by lazy { ItemCharacterBinding.bind(itemView) }

    fun bind(character: Character) = with(binding) {
        characterName.text = character.name
        characterPicture.load(character.thumbnailPath) {
            placeholder(R.drawable.ic_character_placeholder)
            crossfade(true)
        }
    }

}