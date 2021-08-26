package ximenapps.com.br.marvelbynatan.features.characters.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.common.presentation.error.UIError
import ximenapps.com.br.marvelbynatan.databinding.ActivityCharactersBinding
import ximenapps.com.br.marvelbynatan.features.characters.domain.model.Character
import ximenapps.com.br.marvelbynatan.features.characters.presentation.adapter.CharactersAdapter

class CharactersActivity : AppCompatActivity() {
    private val binding: ActivityCharactersBinding by lazy {
        ActivityCharactersBinding.inflate(layoutInflater)
    }
    private val charactersAdapter = CharactersAdapter()
    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.charactersContainer)
        setupObserver()
        setupRecyclerView()
    }

    private fun setupObserver() {
        viewModel.charactersUIStateLiveData.observe(this) { state ->
            when {
                state.showSuccess -> showCharacters(state.charactersList)
                state.showError -> showError(state.uiError)
            }

            setupVisibilities(state)
        }
    }

    private fun showCharacters(charactersList: List<Character>) {
        charactersAdapter.submitList(charactersList)
    }

    private fun showError(uiError: UIError) = with(binding.charactersErrorView) {
        setIcon(uiError.icon)
        setTitle(uiError.title)
        setMessage(uiError.message)
        setupButtonAction(R.string.ui_try_again_button) {
            viewModel.onClickErrorButton()
        }
    }

    private fun setupVisibilities(state: CharactersUIState) = with(binding) {
        charactersRecyclerView.isVisible = state.showSuccess
        charactersErrorView.isVisible = state.showError
        charactersProgress.isVisible = state.isLoading
    }

    private fun setupRecyclerView() {
        binding.charactersRecyclerView.adapter = charactersAdapter
    }
}