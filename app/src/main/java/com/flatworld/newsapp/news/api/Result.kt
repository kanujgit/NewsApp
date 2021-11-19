package com.flatworld.newsapp.news.api

import com.flatworld.newsapp.news.model.Error
import com.flatworld.newsapp.news.model.NewsResponse

sealed class ResultType {
    data class Data(val newsArticle: NewsResponse) : ResultType()
    data class ErrorData(val error: Error) : ResultType()
    data class ErrorUnknown(val error: String) : ResultType()

}