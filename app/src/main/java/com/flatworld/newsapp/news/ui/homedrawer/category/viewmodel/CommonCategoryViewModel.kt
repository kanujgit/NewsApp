package com.flatworld.newsapp.news.ui.homedrawer.category.viewmodel

import androidx.lifecycle.liveData
import com.flatworld.newsapp.core.ui.base.BaseViewModel
import com.flatworld.newsapp.news.repository.CommonRepo
import kotlinx.coroutines.Dispatchers
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
}