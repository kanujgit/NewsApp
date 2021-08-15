package com.flatworld.newsapp.news.ui.homedrawer.category.world

import androidx.lifecycle.liveData
import com.flatworld.newsapp.core.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class WorldViewModel : BaseViewModel<WorldRepository>() {


    fun fetchWorldHeadlines() = liveData(Dispatchers.IO) {
        try {
            getRepo()?.let {
            //    emit(it.getFreshNewsFromWebService())
                if(it.getFreshNewsFromWebService().isSuccessful){
                    it.getFreshNewsFromWebService().body()?.let { it1 -> emit(it1.articles) }
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}