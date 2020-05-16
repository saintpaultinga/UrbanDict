package com.tsp.learn.android.core.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class SearchResultEntity(@JsonProperty("list") val resultList: List<ResultEntry>)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ResultEntry(@JsonProperty("definition") val definition: String,
                  @JsonProperty("example") val example: String,
                  @JsonProperty("thumbs_up") val thumbsUp: Int,
                  @JsonProperty("thumbs_down") val thumbsDown: Int)