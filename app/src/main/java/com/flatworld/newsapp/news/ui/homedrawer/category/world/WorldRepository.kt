package com.flatworld.newsapp.news.ui.homedrawer.category.world

import com.flatworld.newsapp.core.extensions.httpError
import com.flatworld.newsapp.core.network.ApiClient
import com.flatworld.newsapp.news.model.NewsResponse
import retrofit2.Response

class WorldRepository {
    suspend fun getFreshNewsFromWebService(): Response<NewsResponse> {
        return try {
            ApiClient.request!!.getTopHeadlines()
        } catch (e: Exception) {
            httpError(404)
        }
    }
}
