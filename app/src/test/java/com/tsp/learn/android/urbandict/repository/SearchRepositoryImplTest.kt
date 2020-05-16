package com.tsp.learn.android.urbandict.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.tsp.learn.android.core.data.ResultEntry
import com.tsp.learn.android.core.data.SearchResultEntity
import com.tsp.learn.android.core.service.SearchService
import com.tsp.learn.android.urbandict.mapper.SearchMapper
import com.tsp.learn.android.urbandict.model.SearchResult
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {

    private val mockSearchService = mock<SearchService>()

    private lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {
        searchRepository = SearchRepositoryImpl(mockSearchService)
    }

    @Test
    fun testSearchTerm() {
        doReturn(Observable.just(demandSearchResultEntity())).whenever(mockSearchService).searchFor(TERM)
        val testObserver = TestObserver<List<SearchResult>>()

        val observable = searchRepository.searchTerm(TERM)
        observable.subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue(SearchMapper.apply(TERM, demandSearchResultEntity()))
    }

    private fun demandSearchResultEntity() =
        SearchResultEntity(
            listOf(
                ResultEntry(
                    "when you want to understand why",
                    "what is happening",
                    342,
                    567
                )
            )
        )

    private companion object {
        private const val TERM = "what"
    }

}