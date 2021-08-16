package com.flatworld.newsapp.news.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.flatworld.newsapp.news.storage.entity.NewsArticleDb
import kotlinx.coroutines.flow.Flow


@Dao
interface BookmarkDao {

    /**
     * Insert articles into the table
     */
    @Insert
    suspend fun insertArticles(articles: NewsArticleDb): Long

    @Query("SELECT EXISTS (SELECT 1 FROM news_article WHERE title = :title and description=:desc)")
    suspend fun getArticleDetail(title: String, desc: String): Boolean

    @Query("DELETE FROM news_article WHERE title = :title and description=:desc")
    suspend fun clearArticle(title: String, desc: String)

    @Query("DELETE FROM news_article")
    fun clearAllArticles()

    @Transaction
    fun clearAndCacheArticles(articles: List<NewsArticleDb>) {
        clearAllArticles()
    }

    /**
     * Get all the articles from table
     */
    @Query("SELECT * FROM news_article")
    fun getNewsArticles(): Flow<List<NewsArticleDb>>

}