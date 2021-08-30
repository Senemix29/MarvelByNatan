package ximenapps.com.br.marvelbynatan.features.characters.presentation.utils

import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ximenapps.com.br.marvelbynatan.network.infrastructure.NetworkInfraStructure
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreatorImpl

class CustomNetworkDI {
    private val networkInfraStructure = NetworkInfraStructure()

    fun loadWith(url: String, okHttpClient: OkHttpClient? = null) {
        val retrofit = networkInfraStructure.getRetrofit(url, okHttpClient)
        val networkModule = module { factory<ServiceCreator> { ServiceCreatorImpl(retrofit) } }
        loadKoinModules(networkModule)
    }
}