package ximenapps.com.br.marvelbynatan.service

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ximenapps.com.br.marvelbynatan.model.CharactersDataWrapper

interface CharactersService {
    @GET("characters")
    fun listCharacters(): Single<CharactersDataWrapper>
}