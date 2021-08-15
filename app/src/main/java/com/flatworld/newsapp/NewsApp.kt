package com.flatworld.newsapp

import android.app.Application
import com.flatworld.newsapp.core.network.ApiClient
import timber.log.Timber

class NewsApp : Application() {

    companion object {
        lateinit var instance: NewsApp
        fun get(): NewsApp {
            return instance
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        // Plant timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        ApiClient.init()


    }
}