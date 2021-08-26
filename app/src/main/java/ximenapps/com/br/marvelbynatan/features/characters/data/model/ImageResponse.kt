package ximenapps.com.br.marvelbynatan.features.characters.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    @SerialName("path") val path: String,
    @SerialName("extension") val extension: String
)