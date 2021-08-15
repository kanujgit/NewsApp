package com.flatworld.newsapp.news.storage

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flatworld.newsapp.NewsApp
import com.flatworld.newsapp.news.storage.entity.NewsBookmark

@Database(entities = [NewsBookmark::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun BookmarkDao(): BookmarkDao

    val db = Room.databaseBuilder(
        NewsApp.instance,
        NewsDatabase::class.java, "news-database"
    ).build()
}