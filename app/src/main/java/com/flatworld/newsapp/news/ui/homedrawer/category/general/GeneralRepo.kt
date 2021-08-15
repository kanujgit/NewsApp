package com.flatworld.newsapp.news.ui.homedrawer.category.general

import com.flatworld.newsapp.core.extensions.httpError
import com.flatworld.newsapp.core.network.ApiClient
import com.flatworld.newsapp.news.model.NewsResponse
import retrofit2.Response

/**
 * Unused class
 * Create new common Repo class
 */
class GeneralRepo {
    suspend fun getFreshNewsFromWebService(category: String): Response<NewsResponse> {
        return try {
            ApiClient.request!!.getTopHeadlines(category)
        } catch (e: Exception) {
            httpError(404)
        }
    }
}
