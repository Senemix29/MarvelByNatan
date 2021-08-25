package ximenapps.com.br.marvelbynatan.features.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ximenapps.com.br.marvelbynatan.common.presentation.error.UIErrorResolver
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character
import ximenapps.com.br.marvelbynatan.features.characters.domain.usecase.GetCharactersUseCase

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val UIErrorResolver: UIErrorResolver
) : ViewModel() {
    private val _charactersUIStateMutableLiveData = MutableLiveData(CharactersUIState())
    val charactersUIStateLiveData: LiveData<CharactersUIState> = _charactersUIStateMutableLiveData

    private val disposableContainer = CompositeDisposable()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(::onSubscribe)
            .subscribe(::onSuccess, ::onError)
            .handleDisposable()
    }

    private fun onSubscribe(disposable: Disposable) {
        setState { it.switchToLoading() }
    }

    private fun onSuccess(charactersList: List<Character>) {
        setState { it.switchToSuccess(charactersList) }
    }

    private fun onError(throwable: Throwable) {
        setState { it.switchToError(UIErrorResolver.resolve(throwable)) }
    }

    override fun onCleared() {
        disposableContainer.dispose()
        super.onCleared()
    }

    private fun Disposable.handleDisposable() {
        disposableContainer.add(this)
    }

    private fun setState(updateState: (CharactersUIState) -> CharactersUIState) {
        _charactersUIStateMutableLiveData.apply {
            value = value?.let(updateState)
        }
    }
}