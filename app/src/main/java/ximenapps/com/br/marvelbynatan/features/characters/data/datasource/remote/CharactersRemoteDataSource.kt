package ximenapps.com.br.marvelbynatan.features.characters.data.datasource.remote

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharacterResponse

interface CharactersRemoteDataSource {
    fun listCharacters(): Single<List<CharacterResponse>>
}