package ximenapps.com.br.marvelbynatan.features.characters.presentation.utils

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ximenapps.com.br.marvelbynatan.network.infrastructure.NetworkInfraStructure
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreatorImpl

class CustomNetworkDI {
    private val networkInfraStructure = NetworkInfraStructure()

    fun loadWith(url: String) {
        val retrofit = networkInfraStructure.getRetrofit(url)
        val networkModule = module { factory<ServiceCreator> { ServiceCreatorImpl(retrofit) } }
        loadKoinModules(networkModule)
    }
}