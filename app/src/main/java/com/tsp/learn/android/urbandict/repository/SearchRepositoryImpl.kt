package com.tsp.learn.android.urbandict.repository


import com.tsp.learn.android.core.service.SearchService
import com.tsp.learn.android.urbandict.mapper.SearchMapper
import com.tsp.learn.android.urbandict.model.SearchResult
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchService: SearchService) :
    SearchRepository {

    override fun searchTerm(term: String): Observable<List<SearchResult>> {
        return searchService.searchFor(term).cache()
            .flatMap { entities -> Observable.just(SearchMapper.apply(term, entities)) }
    }
}