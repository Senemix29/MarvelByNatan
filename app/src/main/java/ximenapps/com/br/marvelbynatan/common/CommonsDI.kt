package ximenapps.com.br.marvelbynatan.common

import ximenapps.com.br.marvelbynatan.common.presentation.error.UIErrorResolver
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class CommonsDI {
    private val errorModule = module {
        factory { UIErrorResolver() }
    }

    fun load() {
        loadKoinModules(errorModule)
    }
}