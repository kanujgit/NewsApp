package com.flatworld.newsapp.core.ui.base

import androidx.lifecycle.ViewModel


/**
 * Base of view model
 * we can add more generic parameter depends on the use cse
 */
open class BaseViewModel<R> : ViewModel() {

    private var mRepo: R? = null

    fun getRepo(): R? {
        return mRepo
    }

    fun setRepo(repo: R) {
        this.mRepo = repo
    }
}