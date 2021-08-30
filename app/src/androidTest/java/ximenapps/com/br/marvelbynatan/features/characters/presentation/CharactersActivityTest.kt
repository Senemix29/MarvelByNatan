package ximenapps.com.br.marvelbynatan.features.characters.presentation

import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.features.characters.presentation.robots.CharactersActionRobot.Companion.act
import ximenapps.com.br.marvelbynatan.features.characters.presentation.robots.CharactersAssertionRobot.Companion.check
import ximenapps.com.br.marvelbynatan.features.characters.presentation.robots.arrange

class CharactersActivityTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    @Test
    fun shouldShowCharacterList_whenApiReturnsSuccess() {
        arrange {
            mockApiWithSuccessResponse()
            loadNetworkDependencies()
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

    @Test
    fun shouldShowNetworkErrorFeedback_whenItHappens() {
        arrange {
            mockApiWithNetworkErrorResponse()
            loadNetworkErrorDependencies()
            loadRegularDependencies()
            startActivity()
        }
        check {
            errorIconIs(R.drawable.ic_connection_problem)
            errorTitleIs("Connection Error")
            errorMessageIs("You should check your internet connection")
            errorButtonTextIs("Try again")
        }
    }

    @Test
    fun shouldShowApiErrorFeedback_whenItHappens() {
        arrange {
            mockApiWithHttpErrorResponse()
            loadNetworkDependencies()
            loadRegularDependencies()
            startActivity()
        }
        check {
            errorIconIs(R.drawable.ic_api_problem)
            errorTitleIs("Strange Clouds in the air")
            errorMessageIs("Sorry, we are doing our best to fix this problem")
            errorButtonTextIs("Try again")
        }
    }

    @Test
    fun shouldShowGenericErrorFeedback_whenApiReturnsInvalidJson() {
        arrange {
            mockApiWithParseErrorResponse()
            loadNetworkDependencies()
            loadRegularDependencies()
            startActivity()
        }
        check {
            errorIconIs(R.drawable.ic_generic_error)
            errorTitleIs("We have a problem")
            errorMessageIs("Sorry, something wrong happened")
            errorButtonTextIs("Try again")
        }
    }

    @Test
    fun shouldRecoverFromError_AndShowCharactersList_whenClickRetry() {
        arrange {
            mockApiWithHttpErroRecoverResponse()
            loadNetworkDependencies()
            loadRegularDependencies()
            startActivity()
        }
        act {
            clickErrorButton()
        }
        check {
            characterNameAtPosition("3-D Man", 0)
            characterNameAtPosition("Abomination (Emil Blonsky)", 4)
            characterNameAtPosition("Adam Warlock", 10)
            listSizeIs(11)
        }
    }
}