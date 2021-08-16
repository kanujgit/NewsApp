package com.flatworld.newsapp.news.model


data class Error(
    val status: String,
    val code: String,
    val message: String
)