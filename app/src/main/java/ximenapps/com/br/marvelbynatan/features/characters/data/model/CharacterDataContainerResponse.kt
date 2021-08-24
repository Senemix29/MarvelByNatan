package ximenapps.com.br.marvelbynatan.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
class CharacterDataContainerResponse(val total: Int, val results: List<CharacterResponse>)
