package ximenapps.com.br.marvelbynatan.features.characters.domain.repository

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.features.characters.data.datasource.remote.CharactersRemoteDataSource
import ximenapps.com.br.marvelbynatan.features.characters.domain.mapper.CharacterMapper
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character

class CharactersRepositoryImpl(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val mapper: CharacterMapper
    ) : CharactersRepository {
    override fun getCharacters(): Single<List<Character>> {
        return remoteDataSource.getCharacters().map(mapper::map)
    }
}