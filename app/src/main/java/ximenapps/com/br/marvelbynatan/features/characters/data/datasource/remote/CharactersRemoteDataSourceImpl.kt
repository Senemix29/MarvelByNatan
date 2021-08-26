package ximenapps.com.br.marvelbynatan.features.characters.data.datasource.remote

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.common.data.error.extensions.handleRequestExceptions
import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharacterResponse
import ximenapps.com.br.marvelbynatan.features.characters.data.model.CharactersDataWrapperResponse
import ximenapps.com.br.marvelbynatan.features.characters.data.api.CharactersService

class CharactersRemoteDataSourceImpl(
    private val service: CharactersService
) : CharactersRemoteDataSource {
    override fun getCharacters(): Single<List<CharacterResponse>> {
        return service.getCharacters().unwrap().handleRequestExceptions()
    }

    private fun Single<CharactersDataWrapperResponse>.unwrap(): Single<List<CharacterResponse>> {
        return map { it.data.results }
    }
}