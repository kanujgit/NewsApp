package com.flatworld.newsapp.news.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults: Int = 0,

    @SerializedName("articles")
    val articles: List<NewsArticle> = emptyList(),

    @SerializedName("code")
    val code: String = "",

    @SerializedName("message")
    val message: String = ""
)
