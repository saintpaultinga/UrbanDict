package com.tsp.learn.android.urbandict.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.tsp.learn.android.urbandict.model.SearchResult
import java.util.*

class SearchResultDiffUtil: DiffUtil.ItemCallback<SearchResult>() {

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        Objects.equals(oldItem, newItem)

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        Objects.equals(oldItem.hashCode(), newItem.hashCode())
}