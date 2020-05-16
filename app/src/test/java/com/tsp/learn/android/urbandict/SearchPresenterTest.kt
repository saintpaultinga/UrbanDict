package com.tsp.learn.android.urbandict

import com.nhaarman.mockitokotlin2.*
import com.tsp.learn.android.urbandict.contract.SearchContract
import com.tsp.learn.android.urbandict.model.SearchResult
import com.tsp.learn.android.urbandict.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SearchPresenterTest {

    private val mockRepository = mock<SearchRepository>()
    private val mockView = mock<SearchContract.View>()
    private lateinit var searchPresenter: SearchPresenter

    @Before
    fun setUp() {
        searchPresenter = SearchPresenter(mockRepository)
    }

    @Test
    fun testSearchTermWithoutError() {
        searchPresenter = spy(searchPresenter)
        searchPresenter.attachView(mockView)
        doReturn(Observable.just(demandSearchResult())).whenever(mockRepository).searchTerm(TERM)
        doReturn(Schedulers.trampoline()).whenever(searchPresenter).io()
        doReturn(Observable.just(demandSearchResult())).whenever(searchPresenter)
            .bind(any<Observable<List<SearchResult>>>())

        searchPresenter.searchTerm(TERM)

        verify(mockView).onSearchResultReceived(demandSearchResult())
        verify(mockRepository).searchTerm(TERM)
        verify(searchPresenter).io()
        verify(searchPresenter).ui()
        verify(searchPresenter).bind(any<Observable<List<SearchResult>>>())
    }

    @Test
    fun testSearchTermWithError() {
        searchPresenter = spy(searchPresenter)
        searchPresenter.attachView(mockView)
        doReturn(Observable.error<List<SearchResult>>(Throwable("Failed!"))).whenever(mockRepository)
            .searchTerm(TERM)
        doReturn(Observable.error<List<SearchResult>>(Throwable("Failed!"))).whenever(searchPresenter)
            .bind(any<Observable<List<SearchResult>>>())
        doReturn(Schedulers.trampoline()).whenever(searchPresenter).io()

        searchPresenter.searchTerm(TERM)

        verify(mockView).showError()
        verify(mockRepository).searchTerm(TERM)
        verify(searchPresenter).io()
        verify(searchPresenter).ui()
    }

    private fun demandSearchResult() = listOf(
        SearchResult (
            "what",
            "when you want to understand why",
            "what is happening",
            342,
            567,
            1
        )
    )

    private companion object {
        private const val TERM = "what"
    }
}