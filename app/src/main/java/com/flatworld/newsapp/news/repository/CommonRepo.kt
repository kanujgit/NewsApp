package com.flatworld.newsapp.news.repository

import com.flatworld.newsapp.core.network.ApiClient
import com.flatworld.newsapp.core.network.NetworkResponse
import com.flatworld.newsapp.news.model.Error
import com.flatworld.newsapp.news.model.NewsResponse

class CommonRepo {

    suspend fun getFreshNewsFromWebService(category: String): NetworkResponse<NewsResponse, Error> {
        return ApiClient.request!!.getTopHeadlines(category)
    }
}