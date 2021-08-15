package com.flatworld.newsapp.news.ui.details

import androidx.lifecycle.ViewModel
import com.flatworld.newsapp.NewsApp
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.storage.NewsDatabase

class NewsDetailViewModel : ViewModel() {

    private val db by lazy { NewsDatabase(NewsApp.instance).BookmarkDao() }

    fun insertArticles(article: NewsArticle) {
        db.insertArticles(articles = article)
    }

}