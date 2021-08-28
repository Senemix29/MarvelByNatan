package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import androidx.test.core.app.ActivityScenario
import okhttp3.mockwebserver.MockWebServer
import ximenapps.com.br.marvelbynatan.common.CommonsDI
import ximenapps.com.br.marvelbynatan.features.characters.di.CharactersDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivity
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivityTest
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.CustomNetworkDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.initDispatcher
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.mockRequest

private const val CHARACTERS_ENDPOINT = "/characters"
private const val CHARACTERS_SUCCESS_RESPONSE = "characters/characters_success_response.json"

class CharactersArrangementRobot(private val mockWebServer: MockWebServer) {
    private val commonsDI = CommonsDI()
    private val charactersDI = CharactersDI()
    private val customNetworkDI = CustomNetworkDI()

    fun loadDependencies() {
        val mockWebServerUrl = mockWebServer.url("/").toString()
        customNetworkDI.loadWith(mockWebServerUrl)

        commonsDI.load()
        charactersDI.load()
    }

    fun mockApiWithSuccessResponse() {
        mockRequest(CHARACTERS_ENDPOINT, CHARACTERS_SUCCESS_RESPONSE)
        mockWebServer.initDispatcher()
    }

    fun startActivity() {
        ActivityScenario.launch(CharactersActivity::class.java)
    }
}

fun CharactersActivityTest.arrange(block: CharactersArrangementRobot.() -> Unit) {
    CharactersArrangementRobot(mockWebServer).apply(block)
}