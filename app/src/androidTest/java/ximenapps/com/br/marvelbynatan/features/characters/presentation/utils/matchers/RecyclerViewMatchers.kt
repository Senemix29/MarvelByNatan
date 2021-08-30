package ximenapps.com.br.marvelbynatan.features.characters.presentation.utils.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

// This is an adaption from this solution here: https://stackoverflow.com/a/34795431
object RecyclerViewMatchers {
    fun atPosition(
        position: Int,
        itemMatcher: Matcher<View>
    ) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has item at position $position: ")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            val viewHolder = item?.findViewHolderForAdapterPosition(position) ?: return false
            return itemMatcher.matches(viewHolder.itemView)
        }
    }

    fun itemsCount(quantity: Int) =
        ViewAssertion { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            val recyclerViewItemCount = adapter?.itemCount ?: 0
            assertThat(true, CoreMatchers.`is`(recyclerViewItemCount == quantity))
        }
}