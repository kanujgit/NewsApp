package com.flatworld.newsapp.news.ui.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.flatworld.newsapp.NewsApp
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.storage.NewsDatabase
import com.flatworld.newsapp.news.storage.entity.NewsArticleDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class NewsDetailViewModel : ViewModel() {

    private val db by lazy { NewsDatabase(NewsApp.instance).BookmarkDao() }

    fun cacheArticle(article: NewsArticle) = liveData(Dispatchers.Default) {
        coroutineScope {
            val isArticleSaved =
                async {
                    db.getArticleDetail(
                        title = article.title!!,
                        desc = article.description!!
                    )
                }

            if (isArticleSaved.await()) {
                db.clearArticle(title = article.title!!, desc = article.description!!)
                emit("clear")
            } else {
                db.insertArticles(articles = article.toNewsArticleDb())
                emit("saved")

            }

        }
    }


    fun checkCacheItem(article: NewsArticle) = liveData(Dispatchers.Default) {
        coroutineScope {
            val isArticleSaved =
                async {
                    db.getArticleDetail(
                        title = article.title!!,
                        desc = article.description!!
                    )
                }
            if (isArticleSaved.await()) {
                emit("saved")
            } else {
                emit("clear")
            }
        }
    }

}


// make compatible to newsArticleDatabase class
fun NewsArticle.toNewsArticleDb() = NewsArticleDb(
    source = NewsArticleDb.Source(id = source.id, name = source.name),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content,
)