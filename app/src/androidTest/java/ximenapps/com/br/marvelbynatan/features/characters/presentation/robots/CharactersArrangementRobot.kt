package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import androidx.test.core.app.ActivityScenario
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import ximenapps.com.br.marvelbynatan.common.CommonsDI
import ximenapps.com.br.marvelbynatan.features.characters.di.CharactersDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivity
import ximenapps.com.br.marvelbynatan.features.characters.presentation.CharactersActivityTest
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.CustomNetworkDI
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.initDispatcher
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.mockRequest
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.mockRequestWIthNetworkError
import java.util.concurrent.TimeUnit

private const val CHARACTERS_ENDPOINT = "/characters"
private const val CHARACTERS_SUCCESS_RESPONSE = "characters/characters_success_response.json"
private const val CHARACTERS_HTTP_ERROR_RESPONSE = "characters/characters_http_error_response.json"
private const val CHARACTERS_JSON_ERROR_RESPONSE = "characters/characters_json_parse_error_response.json"

class CharactersArrangementRobot(private val mockWebServer: MockWebServer) {
    private val commonsDI = CommonsDI()
    private val charactersDI = CharactersDI()
    private val customNetworkDI = CustomNetworkDI()
    private val mockWebServerUrl = mockWebServer.url("/").toString()

    fun loadRegularDependencies() {
        commonsDI.load()
        charactersDI.load()
    }

    fun loadNetworkDependencies() {
        customNetworkDI.loadWith(mockWebServerUrl)
    }

    fun loadNetworkErrorDependencies() {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MILLISECONDS)
            .build()
        customNetworkDI.loadWith(mockWebServerUrl, okHttpClient)
    }

    fun mockApiWithSuccessResponse() {
        mockRequest(CHARACTERS_ENDPOINT, CHARACTERS_SUCCESS_RESPONSE)
        mockWebServer.initDispatcher()
    }

    fun mockApiWithHttpErrorResponse() {
        mockRequest(CHARACTERS_ENDPOINT, CHARACTERS_HTTP_ERROR_RESPONSE, 404)
        mockWebServer.initDispatcher()
    }

    fun mockApiWithParseErrorResponse() {
        mockRequest(CHARACTERS_ENDPOINT, CHARACTERS_JSON_ERROR_RESPONSE)
        mockWebServer.initDispatcher()
    }

    fun mockApiWithNetworkErrorResponse() {
        mockRequestWIthNetworkError(CHARACTERS_ENDPOINT)
        mockWebServer.initDispatcher()
    }

    fun mockApiWithHttpErrorRecoverResponse() {
        mockRequest(CHARACTERS_ENDPOINT, CHARACTERS_HTTP_ERROR_RESPONSE, 404)
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