package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.matchers.RecyclerViewMatchers.atPosition
import ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.matchers.RecyclerViewMatchers.itemsCount

class CharactersAssertionRobot {

    fun listSizeIs(size: Int) {
        onView(withId(R.id.charactersRecyclerView)).check(itemsCount(size))
    }

    fun characterNameAtPosition(name: String, position: Int) {
        assertItemOnRecyclerView(
            position = position,
            matcher = hasText(R.id.character_name, name)
        )
    }

    private fun assertItemOnRecyclerView(
        @IdRes id: Int = R.id.charactersRecyclerView,
        position: Int,
        matcher: Matcher<View>
    ) {
        onView(withId(id))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
            .check(matches(atPosition(position, hasDescendant(matcher))))
    }

    private fun hasText(@IdRes id: Int, text: String): Matcher<View> {
        return allOf(withId(id), withText(text))
    }

    companion object {
        fun check(block: CharactersAssertionRobot.() -> Unit) {
            CharactersAssertionRobot().apply(block)
        }
    }
}