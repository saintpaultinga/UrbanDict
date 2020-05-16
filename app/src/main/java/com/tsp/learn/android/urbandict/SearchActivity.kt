package com.tsp.learn.android.urbandict

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import com.tsp.learn.android.core.extensions.makeGone
import com.tsp.learn.android.core.extensions.makeVisible
import com.tsp.learn.android.core.utils.Constants
import com.tsp.learn.android.core.utils.Constants.THUMBS_DOWN_SEARCH_SORT
import com.tsp.learn.android.core.utils.Constants.THUMBS_UP_SEARCH_SORT
import com.tsp.learn.android.urbandict.adapter.SearchResultAdapter
import com.tsp.learn.android.urbandict.contract.SearchContract
import com.tsp.learn.android.urbandict.model.SearchResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sort_bar_layout.*
import timber.log.Timber
import javax.inject.Inject

class SearchActivity : MvpViewStateActivity<SearchContract.View, SearchContract.Presenter, SearchViewState>(),
    SearchContract.View, SearchView.OnQueryTextListener, RadioGroup.OnCheckedChangeListener {

    @Inject
    lateinit var searchPresenter: SearchContract.Presenter

    @Inject
    lateinit var searchViewState: SearchViewState

    private var searchResultView: RecyclerView? = null

    private var searchResultAdapter: SearchResultAdapter? = null
    private var currentSearchTerm: String = Constants.DEFAULT_SEARCH_TEM
    private var radioCheckedId: Int = R.id.default_sort

    init {
        SearchApplication.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchResultView = findViewById(R.id.recyclerView)

        searchResultAdapter = SearchResultAdapter()

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchResultView?.apply {
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = searchResultAdapter
            setHasFixedSize(true)
        }

        searchView.isSubmitButtonEnabled = true
        sortPrefBtn.setOnCheckedChangeListener(this)
        searchView.setOnQueryTextListener(this)
        if (searchViewState.state == SearchViewState.STATE_DO_NOTHING) {
            showLoading()
            searchPresenter.searchTerm(currentSearchTerm)
        } else {
            Timber.d("Skip making a new call, the cached data will be used!!")
        }
    }

    override fun createPresenter(): SearchContract.Presenter = searchPresenter

    override fun onSearchResultReceived(resultList: List<SearchResult>) {
        searchResultAdapter?.submitList(resultList)
        hideLoading()
        searchViewState.saveSearchResult(resultList, currentSearchTerm)
        searchResultView?.smoothScrollToPosition(0)
        if (resultList.isEmpty()) {
            Toast.makeText(this, R.string.no_result_found, Toast.LENGTH_LONG).show()
        }
    }

    private fun showLoading() {
        loadingView.makeVisible()
    }

    private fun hideLoading() {
        loadingView.makeGone()
    }

    override fun showError() {
        Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show()
        hideLoading()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        showLoading()
        query?.let {
            currentSearchTerm = it
            searchForTerm(radioCheckedId)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (group != null && group.findViewById<RadioButton>(checkedId).isPressed) {
            searchForTerm(checkedId)
            radioCheckedId = checkedId
        }
    }

    private fun searchForTerm(sortedPref: Int) {
        when (sortedPref) {
            R.id.default_sort -> searchPresenter.searchTerm(currentSearchTerm)
            R.id.thumb_up_sort -> searchPresenter.searchTerm(
                currentSearchTerm,
                THUMBS_UP_SEARCH_SORT
            )
            R.id.thumb_down_sort -> searchPresenter.searchTerm(
                currentSearchTerm,
                THUMBS_DOWN_SEARCH_SORT
            )
        }
    }

    override fun onNewViewStateInstance() {
        // No need to be implemented at this point
    }

    override fun createViewState(): SearchViewState = searchViewState

    override fun replaySearchResultState(searchState: List<SearchResult>?, term: String) {
        currentSearchTerm = term
        searchState?.let { state ->
            searchResultAdapter?.submitList(state)
            searchResultAdapter?.notifyDataSetChanged()
        }
    }
}
