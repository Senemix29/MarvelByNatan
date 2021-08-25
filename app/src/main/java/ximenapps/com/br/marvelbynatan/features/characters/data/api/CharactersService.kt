package ximenapps.com.br.marvelbynatan.features.characters.data.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharactersDataWrapperResponse

interface CharactersService {
    @GET("characters")
    fun getCharacters(): Single<CharactersDataWrapperResponse>
}