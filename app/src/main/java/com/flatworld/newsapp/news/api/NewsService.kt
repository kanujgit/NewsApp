package com.flatworld.newsapp.news.api

import com.flatworld.newsapp.BuildConfig
import com.flatworld.newsapp.news.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    /**
     * Get top headlines.
     *apiKey  =  2d07a75799694e699c8f676b6841de8b
     * See [article documentation](https://newsapi.org/docs/endpoints/top-headlines).
     */
    @GET("top-headlines?apiKey=${BuildConfig.NEWS_API_KEY}&category=general")
    suspend fun getTopHeadlines(): Response<NewsResponse>

}