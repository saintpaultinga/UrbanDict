package com.tsp.learn.android.core.service

import com.tsp.learn.android.core.data.SearchResultEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/define")
    fun searchFor(@Query("term") term: String): Observable<SearchResultEntity>
}