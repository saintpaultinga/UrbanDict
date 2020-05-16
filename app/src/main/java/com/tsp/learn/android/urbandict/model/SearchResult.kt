package com.tsp.learn.android.urbandict.model

data class SearchResult(val term: String,
                        val definition: String,
                        val example: String,
                        val thumbsUp: Int,
                        val thumbsDown: Int,
                        val defRank: Int)