package ximenapps.com.br.marvelbynatan.features.characters.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersDataWrapperResponse(
    @SerialName("data") val data: CharacterDataContainerResponse
)