package com.tsp.learn.android.urbandict.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tsp.learn.android.core.extensions.inflateView
import com.tsp.learn.android.urbandict.R
import com.tsp.learn.android.urbandict.adapter.diffutils.SearchResultDiffUtil
import com.tsp.learn.android.urbandict.adapter.viewholder.SearchResultViewHolder
import com.tsp.learn.android.urbandict.model.SearchResult

class SearchResultAdapter: ListAdapter<SearchResult, SearchResultViewHolder>(SearchResultDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder(
            parent.inflateView(R.layout.search_item_layout)
        )

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}