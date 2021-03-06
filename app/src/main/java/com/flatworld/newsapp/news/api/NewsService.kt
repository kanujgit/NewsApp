package com.flatworld.newsapp.news.api

import com.flatworld.newsapp.BuildConfig
import com.flatworld.newsapp.core.network.NetworkResponse
import com.flatworld.newsapp.news.model.Error
import com.flatworld.newsapp.news.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    /**
     * Get top headlines
     * See [article documentation](https://newsapi.org/docs/endpoints/top-headlines).
     */
    @GET("top-headlines?language=en&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getTopHeadlines(@Query("category") category: String): NetworkResponse<NewsResponse, Error>
}