package ximenapps.com.br.marvelbynatan.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
class CharacterResponse(
    val name: String,
    val description: String,
    val imageResponse: ImageResponse,
)