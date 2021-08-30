package ximenapps.com.br.marvelbynatan.features.characters.presentation

import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import ximenapps.com.br.marvelbynatan.features.characters.presentation.robots.CharactersAssertionRobot.Companion.check
import ximenapps.com.br.marvelbynatan.features.characters.presentation.robots.arrange

class CharactersActivityTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    @Test
    fun shouldShowCharacterList_whenApiReturnsSuccess() {
        arrange {
            mockApiWithSuccessResponse()
            loadRegularDependencies()
            startActivity()
        }
        check {
            characterNameAtPosition("3-D Man", 0)
            characterNameAtPosition("Abomination (Emil Blonsky)", 4)
            characterNameAtPosition("Adam Warlock", 10)
            listSizeIs(11)
        }
    }
}