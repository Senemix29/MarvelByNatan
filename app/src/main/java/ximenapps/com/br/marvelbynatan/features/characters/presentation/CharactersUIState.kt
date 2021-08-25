package ximenapps.com.br.marvelbynatan.features.characters.presentation

import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.common.presentation.error.UIError
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character

data class CharactersUIState(
    val showSuccess: Boolean = false,
    val showError: Boolean = false,
    val isLoading: Boolean = false,
    val charactersList: List<Character> = emptyList(),
    val uiError: UIError = UIError(
        R.string.ui_unknown_error_title,
        R.string.ui_unknown_error_message,
        R.drawable.ic_generic_error
    )
) {
    fun switchToSuccess(charactersList: List<Character>): CharactersUIState {
        return this.copy(
            showSuccess = true,
            showError = false,
            isLoading = false,
            charactersList = charactersList
        )
    }

    fun switchToError(uiError: UIError): CharactersUIState {
        return this.copy(
            showSuccess = false,
            showError = true,
            isLoading = false,
            uiError = uiError
        )
    }

    fun switchToLoading(): CharactersUIState {
        return this.copy(
            showSuccess = false,
            showError = false,
            isLoading = true
        )
    }
}