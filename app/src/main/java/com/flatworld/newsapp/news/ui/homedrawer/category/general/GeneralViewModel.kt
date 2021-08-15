package com.flatworld.newsapp.news.ui.homedrawer.category.general

import androidx.lifecycle.liveData
import com.flatworld.newsapp.core.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class GeneralViewModel : BaseViewModel<GeneralRepo>() {


    fun fetchGeneralHeadlines() = liveData(Dispatchers.IO) {
        try {
            emit("Loading")
            getRepo()?.let {
                if (it.getFreshNewsFromWebService().isSuccessful) {
                    it.getFreshNewsFromWebService().body()?.let { it1 ->
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