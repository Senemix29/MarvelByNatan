package ximenapps.com.br.marvelbynatan.features.characters.presentation

import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import ximenapps.com.br.marvelbynatan.features.characters.presentation.robots.arrange

class CharactersActivityTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    @Test
    fun shouldShowCharacterList_whenApiReturnsSuccess() {
        arrange {
            mockApiWithSuccessResponse()
            loadDependencies()
            startActivity()
        }
    }
}