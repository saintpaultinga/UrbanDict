package com.tsp.learn.android.urbandict

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import com.tsp.learn.android.core.utils.Constants.EMPTY_STRING
import com.tsp.learn.android.urbandict.contract.SearchContract
import com.tsp.learn.android.urbandict.model.SearchResult

class SearchViewState: ViewState<SearchContract.View> {

    private var recyclerViewState: List<SearchResult>? = null
    private var searchedTerm: String = EMPTY_STRING
    var state: Int = STATE_DO_NOTHING

    fun saveSearchResult(listState: List<SearchResult>?, term: String) {
        state = STATE_SHOW_SEARCH_RESULT
        searchedTerm = term
        recyclerViewState = listState
    }

    override fun apply(view: SearchContract.View?, retained: Boolean) {
        when(state) {
            STATE_SHOW_SEARCH_RESULT -> view?.replaySearchResultState(recyclerViewState, searchedTerm)
        }
    }

    companion object {
        const val STATE_SHOW_SEARCH_RESULT = 1
        const val STATE_DO_NOTHING = 0
    }
}