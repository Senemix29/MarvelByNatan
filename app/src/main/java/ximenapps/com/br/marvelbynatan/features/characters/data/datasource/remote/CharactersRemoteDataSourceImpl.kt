package ximenapps.com.br.marvelbynatan.features.characters.data.datasource.remote

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharacterResponse
import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharactersDataWrapperResponse
import ximenapps.com.br.marvelbynatan.features.characters.data.api.CharactersService

class CharactersRemoteDataSourceImpl(
    private val service: CharactersService
) : CharactersRemoteDataSource {
    override fun listCharacters(): Single<List<CharacterResponse>> {
        return service.listCharacters().unwrap()
    }

    private fun Single<CharactersDataWrapperResponse>.unwrap(): Single<List<CharacterResponse>> {
        return map { it.dataResponse.results }
    }
}