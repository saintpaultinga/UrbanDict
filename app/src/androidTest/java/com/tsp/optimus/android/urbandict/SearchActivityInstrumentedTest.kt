package com.tsp.optimus.android.urbandict

import android.view.KeyEvent
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.tsp.learn.android.core.utils.Constants.EMPTY_STRING
import com.tsp.learn.android.urbandict.R
import com.tsp.learn.android.urbandict.SearchActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchActivityInstrumentedTest {

    @get:Rule
    var rule = ActivityTestRule(SearchActivity::class.java)

    private lateinit var searchActivity: SearchActivity

    @Before
    fun setUp() {
        searchActivity = rule.activity
    }

    @Test
    fun testIfViewsRendered() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withId(R.id.default_sort)).check(matches(isChecked()))
        onView(withId(R.id.sortPrefBtn)).check(matches(isDisplayed()))
        onView(withId(R.id.thumb_down_sort)).check(matches(isDisplayed()))
        onView(withId(R.id.thumb_up_sort)).check(matches(isDisplayed()))
    }

    @Test
    fun testPerformSearch() {
        onView(withId(R.id.searchView)).perform(typeSearchViewText("What"))
            .perform(pressKey(KeyEvent.KEYCODE_SEARCH))
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return EMPTY_STRING
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, true)
            }
        }
    }
}