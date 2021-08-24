package ximenapps.com.br.marvelbynatan.network.di

import ximenapps.com.br.marvelbynatan.network.infrastructure.NetworkInfraStructure
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreatorImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class NetworkDI {
    private val dataModule = module {
        val networkInfraStructure = NetworkInfraStructure()
        val retrofit = networkInfraStructure.getRetrofit()
        single<ServiceCreator> { ServiceCreatorImpl(retrofit) }
    }

    fun load() {
        loadKoinModules(dataModule)
    }
}