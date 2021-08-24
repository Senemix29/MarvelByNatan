package ximenapps.com.br.marvelbynatan.features.characters.data.model

import kotlinx.serialization.Serializable

@Serializable
class ImageResponse(
    val path: String,
    val extension: String
)