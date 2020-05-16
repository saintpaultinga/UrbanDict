package com.tsp.learn.android.urbandict.contract

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.tsp.learn.android.urbandict.model.SearchResult
import io.reactivex.Scheduler


interface SearchContract {

    interface View : MvpView {
        /**
         * @param resultList the list of result related to the
         * search term
         */
        fun onSearchResultReceived(resultList: List<SearchResult>)

        /**
         * @param searchState a cache of the search result to be use to populate the
         * result list when configuration change
         * @param term the cached searched term to survive config change
         */
        fun replaySearchResultState(searchState: List<SearchResult>?, term: String)

        /**
         * show error when an issue happens during the data loading
         */
        fun showError()
    }

    interface Presenter : MvpPresenter<View> {
        /**
         * @param term, the term to search the definition for in the
         * urban dictionary
         * @param sortPref the result list sort preference
         */
        fun searchTerm(term: String, sortPref:String = "")

        /**
         * @return rxjava io scheduler
         */
        fun io(): Scheduler

        /**
         * @return rxjava main scheduler
         */
        fun ui(): Scheduler
    }
}