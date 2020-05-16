package com.tsp.learn.android.urbandict.adapter.viewholder

import android.view.ContextThemeWrapper
import android.widget.FrameLayout
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.hasToString
import com.tsp.learn.android.core.extensions.inflateView
import com.tsp.learn.android.urbandict.R
import com.tsp.learn.android.urbandict.model.SearchResult
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchResultViewHolderTest {

    private lateinit var searchResultViewHolder: SearchResultViewHolder

    private lateinit var context: ContextThemeWrapper

    @Before
    fun setUp() {
        context = ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.AppTheme)
        searchResultViewHolder =
            SearchResultViewHolder(FrameLayout(context).inflateView(R.layout.search_item_layout))
    }

    @Test
    fun bind() {
        searchResultViewHolder.bind(demandSearchResult())

        assertThat(searchResultViewHolder.itemView.findViewById<TextView>(R.id.searchTerm).text).hasToString(TERM)
        assertThat(searchResultViewHolder.itemView.findViewById<TextView>(R.id.definitionContentView).text).hasToString(DEFINITION)
        assertThat(searchResultViewHolder.itemView.findViewById<TextView>(R.id.sampleContentView).text).hasToString(EXAMPLE)
        assertThat(searchResultViewHolder.itemView.findViewById<TextView>(R.id.thumbDownView).text).hasToString(THUMBS_DOWN.toString())
        assertThat(searchResultViewHolder.itemView.findViewById<TextView>(R.id.thumbUpView).text).hasToString(THUMBS_UP.toString())
    }

    private fun demandSearchResult() =
        SearchResult(
            TERM,
            DEFINITION,
            EXAMPLE,
            THUMBS_UP,
            THUMBS_DOWN,
            DE_FRANK
        )

    private companion object {
        private const val TERM = "what"
        private const val DEFINITION = "New york Queen"
        private const val EXAMPLE = "234-455-4566"
        private const val THUMBS_UP = 234
        private const val THUMBS_DOWN = 12
        private const val DE_FRANK = 1
    }
}