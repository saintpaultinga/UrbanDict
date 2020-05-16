package com.tsp.learn.android.urbandict.repository

import com.tsp.learn.android.urbandict.model.SearchResult
import io.reactivex.Observable

interface SearchRepository {
    /**
     * @param term, the term to search the definition for in the
     * urban dictionary
     */
    fun searchTerm(term: String) : Observable<List<SearchResult>>
}
