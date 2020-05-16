package com.tsp.learn.android.urbandict.mapper

import com.tsp.learn.android.core.data.ResultEntry
import com.tsp.learn.android.core.data.SearchResultEntity
import com.tsp.learn.android.urbandict.model.SearchResult
import java.util.function.BiFunction

object SearchMapper : BiFunction<String, SearchResultEntity, List<SearchResult>> {

    override fun apply(term: String, entity: SearchResultEntity): List<SearchResult> {
        return entity.resultList.mapIndexed{ i, entry -> map(term, i, entry)}
    }

    private fun map(term: String, index:Int, resultEntry:ResultEntry): SearchResult {
        return SearchResult(
            term,
            resultEntry.definition,
            resultEntry.example,
            resultEntry.thumbsUp,
            resultEntry.thumbsDown,
            index.inc()
        )
    }

}