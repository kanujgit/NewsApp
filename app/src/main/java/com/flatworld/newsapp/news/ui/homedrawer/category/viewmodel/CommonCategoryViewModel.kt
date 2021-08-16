package com.flatworld.newsapp.news.ui.homedrawer.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.flatworld.newsapp.NewsApp
import com.flatworld.newsapp.core.ui.base.BaseViewModel
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.repository.CommonRepo
import com.flatworld.newsapp.news.storage.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class CommonCategoryViewModel : BaseViewModel<CommonRepo>() {

    fun fetchTopHeadlines(category: String) = liveData(Dispatchers.IO) {
        try {
            emit("Loading")
            getRepo()?.let {
                if (it.getFreshNewsFromWebService(category).isSuccessful) {
                    it.getFreshNewsFromWebService(category).body()?.let { it1 ->
                        emit(it1.articles)
                    }
                } else {
                    emit("Error")
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            emit("Error")
        }
    }

    private val db by lazy { NewsDatabase(NewsApp.instance).BookmarkDao() }

    // get all bookmark data
    fun getAllBookMarks() = liveData(Dispatchers.Default) {
        db.getNewsArticles().collect {
            Timber.d("$it")
            emit(it)
        }
    }

    private val mutableSelectedItem = MutableLiveData<NewsArticle>()
    val selectedItem: LiveData<NewsArticle> get() = mutableSelectedItem

    fun selectItem(item: NewsArticle) {
        mutableSelectedItem.value = item
    }

}