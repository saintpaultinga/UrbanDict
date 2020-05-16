package com.tsp.learn.android.urbandict

import android.annotation.SuppressLint
import com.tsp.learn.android.core.mvp.RxBaseMvpPresenter
import com.tsp.learn.android.core.utils.Constants
import com.tsp.learn.android.urbandict.contract.SearchContract
import com.tsp.learn.android.urbandict.model.SearchResult
import com.tsp.learn.android.urbandict.repository.SearchRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        // TODO replace the logging with Timber library and also cover this method with a unit test
        ifViewAttached { view ->
            view.showError()
        }
    }

    private fun sortResult(resultList: List<SearchResult>, sortedPref: String): List<SearchResult> {
        var list = listOf<SearchResult>()
        when (sortedPref) {
            Constants.EMPTY_STRING -> list = resultList
            Constants.THUMBS_UP_SEARCH_SORT ->  list = resultList.sortedByDescending { it.thumbsUp }
            Constants.THUMBS_DOWN_SEARCH_SORT -> list = resultList.sortedBy { it.thumbsDown }
        }
        return list
    }

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}