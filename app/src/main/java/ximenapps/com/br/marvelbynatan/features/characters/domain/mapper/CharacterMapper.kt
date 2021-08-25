package ximenapps.com.br.marvelbynatan.features.characters.domain.mapper

import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharacterResponse
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character

class CharacterMapper {
    fun map(characterResponse: List<CharacterResponse>): List<Character> {
        return characterResponse.map {
            Character(
                name = it.name,
                description = it.description,
                thumbnailPath = "${it.imageResponse.path}.${it.imageResponse.extension}"
            )
        }
    }
}