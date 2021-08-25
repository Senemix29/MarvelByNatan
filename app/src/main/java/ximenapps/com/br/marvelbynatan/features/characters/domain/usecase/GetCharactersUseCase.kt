package ximenapps.com.br.marvelbynatan.features.characters.domain.usecase

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character
import ximenapps.com.br.marvelbynatan.features.characters.domain.repository.CharactersRepository

class GetCharactersUseCase(private val repository: CharactersRepository) {
    operator fun invoke(): Single<List<Character>> {
        return repository.getCharacters()
    }
}