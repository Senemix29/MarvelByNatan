package ximenapps.com.br.marvelbynatan.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val imageResponse: ImageResponse,
)