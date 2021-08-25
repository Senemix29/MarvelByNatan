package ximenapps.com.br.marvelbynatan.features.characters.domain.repository

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character

interface CharactersRepository {
    fun getCharacters(): Single<List<Character>>
}