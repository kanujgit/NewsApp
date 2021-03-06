package com.flatworld.newsapp.news.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flatworld.newsapp.news.storage.dao.BookmarkDao
import com.flatworld.newsapp.news.storage.entity.NewsArticleDb

@Database(entities = [NewsArticleDb::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun BookmarkDao(): BookmarkDao

    companion object {

        private var instance: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news_db"
        ).build()

    }
}