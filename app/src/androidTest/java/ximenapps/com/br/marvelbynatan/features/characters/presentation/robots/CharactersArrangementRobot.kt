package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ximenapps.com.br.marvelbynatan.common.CommonsDI
import ximenapps.com.br.marvelbynatan.features.characters.di.CharactersDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivityTest
import ximenapps.com.br.marvelbynatan.network.infrastructure.NetworkInfraStructure
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreatorImpl

class CharactersArrangementRobot {

    fun setupNetwork(url: String) {
        val retrofit = NetworkInfraStructure().getRetrofit(url)
        val networkModule = module { factory<ServiceCreator> { ServiceCreatorImpl(retrofit) } }
        loadKoinModules(networkModule)
    }

    fun loadDependencies() {
        CommonsDI().load()
        CharactersDI().load()
    }
}

fun CharactersActivityTest.arrange(block: CharactersArrangementRobot.() -> Unit) {
    CharactersArrangementRobot().apply(block)
}