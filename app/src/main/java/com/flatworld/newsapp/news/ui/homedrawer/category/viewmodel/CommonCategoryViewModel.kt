package com.flatworld.newsapp.news.ui.homedrawer.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.flatworld.newsapp.NewsApp
import com.flatworld.newsapp.core.network.NetworkResponse
import com.flatworld.newsapp.core.ui.ViewState
import com.flatworld.newsapp.core.ui.base.BaseViewModel
import com.flatworld.newsapp.news.model.Error
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
                when (val item = it.getFreshNewsFromWebService(category)) {
                    is NetworkResponse.Success -> {
                        emit(item.body.articles)
                        //emit(ResultType.Data(item.body))
                        //emit(ViewState.success(item.body.articles))
                    }
                    is NetworkResponse.ApiError -> {
                        Timber.d("fail ${item.body.message} ")
                        //emit(ResultType.ErrorData(item.body))
                        emit(ViewState.error<Error>(item.body.message))
                        emit("Error")
                    }
//                    is NetworkResponse.UnknownError,
                    is NetworkResponse.NetworkError -> {
                        Timber.d("Network error ")
                        emit(ViewState.error<Error>(item.error.localizedMessage))
                        emit("Error")
                    }
//                    is NetworkResponse.UnknownError -> {
//                        Timber.d("Unknown Error")
//                        emit(ViewState.error<Error>(item.error.localizedMessage))
//                        emit(ResultType.ErrorUnknown("Unknown Error"))
//                        emit("Error")
//                    }
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