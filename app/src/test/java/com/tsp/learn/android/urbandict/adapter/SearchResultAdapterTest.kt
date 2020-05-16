package com.tsp.learn.android.urbandict.adapter

import android.view.ContextThemeWrapper
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import assertk.assertions.isInstanceOf
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tsp.learn.android.urbandict.R
import com.tsp.learn.android.urbandict.adapter.viewholder.SearchResultViewHolder
import com.tsp.learn.android.urbandict.model.SearchResult
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchResultAdapterTest {

    private val mockSearchResultViewHolder = mock<SearchResultViewHolder>()
    private val mockSearchResult = mock<SearchResult>()

    private lateinit var context: ContextThemeWrapper
    private lateinit var searchResultAdapter: SearchResultAdapter

    @Before
    fun setUp() {
        searchResultAdapter = SearchResultAdapter()
        context = ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.AppTheme)
    }

    @Test
    fun testOnCreateViewHolder() {
        assertk.assertThat(searchResultAdapter.createViewHolder(FrameLayout(context), 0))
            .isInstanceOf(SearchResultViewHolder::class.java)
    }

    @Test
    fun testOnBindViewHolder() {
        searchResultAdapter.submitList(listOf(mockSearchResult))

        searchResultAdapter.onBindViewHolder(mockSearchResultViewHolder, 0)

        verify(mockSearchResultViewHolder).bind(mockSearchResult)
    }
}