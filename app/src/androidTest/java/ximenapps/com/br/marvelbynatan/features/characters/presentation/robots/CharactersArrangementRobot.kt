package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import androidx.test.core.app.ActivityScenario
import okhttp3.mockwebserver.MockWebServer
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ximenapps.com.br.marvelbynatan.common.CommonsDI
import ximenapps.com.br.marvelbynatan.features.characters.di.CharactersDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivity
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivityTest
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.initDispatcher
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.mockRequest
import ximenapps.com.br.marvelbynatan.network.infrastructure.NetworkInfraStructure
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreator
import ximenapps.com.br.marvelbynatan.network.service.ServiceCreatorImpl

private const val CHARACTERS_ENDPOINT = "characters"
private const val CHARACTERS_SUCCESS_RESPONSE = "/characters/characters_success_response.json"

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

    fun mockApiWithSuccessResponse() {
        mockWebServer.mockRequest(CHARACTERS_ENDPOINT, CHARACTERS_SUCCESS_RESPONSE)
        mockWebServer.initDispatcher()
    }

    fun startActivity() {
        ActivityScenario.launch(CharactersActivity::class.java)
    }
}

fun CharactersActivityTest.arrange(block: CharactersArrangementRobot.() -> Unit) {
    CharactersArrangementRobot(mockWebServer).apply(block)
}