package com.flatworld.newsapp.news.repository

import com.flatworld.newsapp.core.extensions.httpError
import com.flatworld.newsapp.core.network.ApiClient
import com.flatworld.newsapp.news.model.NewsResponse
import retrofit2.Response

class CommonRepo {

    suspend fun getFreshNewsFromWebService(category: String): Response<NewsResponse> {
        return try {
            ApiClient.request!!.getTopHeadlines(category)
        } catch (e: Exception) {
            httpError(404)
        }
    }
}