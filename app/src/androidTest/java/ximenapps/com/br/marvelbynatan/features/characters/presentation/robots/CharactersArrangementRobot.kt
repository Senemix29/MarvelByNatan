package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import okhttp3.mockwebserver.MockWebServer
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ximenapps.com.br.marvelbynatan.common.CommonsDI
import ximenapps.com.br.marvelbynatan.features.characters.di.CharactersDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivityTest
import ximenapps.com.br.marvelbynatan.network.infrastructure.NetworkInfraStructure
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreatorImpl

class CharactersArrangementRobot(private val mockWebServer: MockWebServer) {
    private val networkInfraStructure = NetworkInfraStructure()

    fun setupNetwork() {
        val mockWebServerUrl = mockWebServer.url("/").toString()
        val retrofit = networkInfraStructure.getRetrofit(mockWebServerUrl)
        val networkModule = module { factory<ServiceCreator> { ServiceCreatorImpl(retrofit) } }
        loadKoinModules(networkModule)
    }

    fun loadDependencies() {
        CommonsDI().load()
        CharactersDI().load()
    }
}

fun CharactersActivityTest.arrange(block: CharactersArrangementRobot.() -> Unit) {
    CharactersArrangementRobot(mockWebServer).apply(block)
}