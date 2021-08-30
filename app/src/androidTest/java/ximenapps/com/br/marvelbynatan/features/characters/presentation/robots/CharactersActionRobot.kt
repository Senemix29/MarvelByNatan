package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import ximenapps.com.br.marvelbynatan.R

class CharactersActionRobot {
    fun clickErrorButton() {
        onView(withId(R.id.errorViewButton)).perform(click())
    }

    companion object {
        fun act(block: CharactersActionRobot.() -> Unit) {
            CharactersActionRobot().apply(block)
        }
    }
}