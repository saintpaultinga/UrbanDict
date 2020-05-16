package com.tsp.learn.android.urbandict.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsp.learn.android.urbandict.R
import com.tsp.learn.android.urbandict.model.SearchResult

class SearchResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val termView = itemView.findViewById<TextView>(R.id.searchTerm)
    private val definitionContentView = itemView.findViewById<TextView>(R.id.definitionContentView)
    private val sampleContentView = itemView.findViewById<TextView>(R.id.sampleContentView)
    private val thumbsUpView = itemView.findViewById<TextView>(R.id.thumbUpView)
    private val thumbsDownView = itemView.findViewById<TextView>(R.id.thumbDownView)
    private val definitionRankView = itemView.findViewById<TextView>(R.id.rankView)

    fun bind(searchResult: SearchResult) {
        display(searchResult)
    }

    private fun display(searchResult: SearchResult) {
        termView.text = searchResult.term
        definitionContentView.text = searchResult.definition
        sampleContentView.text = searchResult.example
        thumbsUpView.text = searchResult.thumbsUp.toString()
        thumbsDownView.text = searchResult.thumbsDown.toString()
        definitionRankView.text = searchResult.defRank.toString()
    }
}