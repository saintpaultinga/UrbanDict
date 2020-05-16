package com.tsp.learn.android.urbandict

import android.annotation.SuppressLint
import com.tsp.learn.android.core.mvp.RxBaseMvpPresenter
import com.tsp.learn.android.core.utils.Constants.EMPTY_STRING
import com.tsp.learn.android.core.utils.Constants.THUMBS_DOWN_SEARCH_SORT
import com.tsp.learn.android.core.utils.Constants.THUMBS_UP_SEARCH_SORT
import com.tsp.learn.android.urbandict.contract.SearchContract
import com.tsp.learn.android.urbandict.model.SearchResult
import com.tsp.learn.android.urbandict.repository.SearchRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val searchRepository: SearchRepository):
    RxBaseMvpPresenter<SearchContract.View>(), SearchContract.Presenter {

    @SuppressLint("CheckResult")
    override fun searchTerm(term: String, sortPref:String) {
        bind(searchRepository.searchTerm(term))
            .subscribeOn(io())
            .observeOn(ui())
            .subscribe({ resultList ->
                ifViewAttached { view ->
                    view.onSearchResultReceived(sortResult(resultList, sortPref))
                }
            }, this::displayError)
    }

    private fun displayError(error: Throwable) {
        ifViewAttached { view ->
            Timber.e(error, "Error retrieving the term definition!!")
            view.showError()
        }
    }

    private fun sortResult(resultList: List<SearchResult>, sortedPref: String): List<SearchResult> {
        var list = listOf<SearchResult>()
        when (sortedPref) {
            EMPTY_STRING -> list = resultList
            THUMBS_UP_SEARCH_SORT ->  list = resultList.sortedByDescending { it.thumbsUp }
            THUMBS_DOWN_SEARCH_SORT -> list = resultList.sortedBy { it.thumbsDown }
        }
        return list
    }

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}