package ximenapps.com.br.marvelbynatan.features.characters.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ximenapps.com.br.marvelbynatan.features.characters.data.api.CharactersService
import ximenapps.com.br.marvelbynatan.features.characters.data.datasource.remote.CharactersRemoteDataSource
import ximenapps.com.br.marvelbynatan.features.characters.data.datasource.remote.CharactersRemoteDataSourceImpl
import ximenapps.com.br.marvelbynatan.features.characters.domain.mapper.CharacterMapper
import ximenapps.com.br.marvelbynatan.features.characters.domain.repository.CharactersRepository
import ximenapps.com.br.marvelbynatan.features.characters.domain.repository.CharactersRepositoryImpl
import ximenapps.com.br.marvelbynatan.features.characters.domain.usecase.GetCharactersUseCase
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersViewModel
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator

class CharactersDI {
    private val presentationModule = module {
        viewModel { CharactersViewModel(getCharactersUseCase = get(), UIErrorResolver = get()) }
    }

    private val domainModule = module {
        factory {
            GetCharactersUseCase(
                repository = get()
            )
        }
    }

    private val dataModule = module {
        factory { get<ServiceCreator>().create(CharactersService::class.java) }
        factory<CharactersRemoteDataSource> { CharactersRemoteDataSourceImpl(service = get()) }
        factory<CharactersRepository> {
            CharactersRepositoryImpl(
                remoteDataSource = get(),
                mapper = CharacterMapper()
            )
        }
    }

    fun load() {
        loadKoinModules(presentationModule + domainModule + dataModule)
    }
}