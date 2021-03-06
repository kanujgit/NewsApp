package com.flatworld.newsapp.news.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Describes how the news article are stored.
 */
@Entity(tableName = NewsArticleDb.NewsArticles.tableName)
data class NewsArticleDb(

    /**
     * Primary key for Room.
     */
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    /**
     * Name of the author for the article
     */
    @ColumnInfo(name = NewsArticles.Column.author)
    var author: String? = null,

    /**
     * Title of the article
     */
    @ColumnInfo(name = NewsArticles.Column.title)
    var title: String? = null,

    /**
     * Complete description of the article
     */
    @ColumnInfo(name = NewsArticles.Column.description)
    var description: String? = null,

    /**
     * URL to the article
     */
    @ColumnInfo(name = NewsArticles.Column.url)
    var url: String? = null,

    /**
     * URL of the artwork shown with article
     */
    @ColumnInfo(name = NewsArticles.Column.urlToImage)
    var urlToImage: String? = null,

    /**
     * Date-time when the article was published
     */
    @ColumnInfo(name = NewsArticles.Column.publishedAt)
    var publishedAt: String? = null,

    @Embedded(prefix = "source_")
    var source: Source,

    @ColumnInfo(name = NewsArticles.Column.content)
    var content: String? = null
) {

    data class Source(
        @ColumnInfo(name = NewsArticles.Column.sourceId)
        var id: String? = null,

        @ColumnInfo(name = NewsArticles.Column.sourceName)
        var name: String? = null
    )

    object NewsArticles {
        const val tableName = "news_article"

        object Column {
            const val id = "id"
            const val author = "author"
            const val title = "title"
            const val description = "description"
            const val url = "url"
            const val urlToImage = "urlToImage"
            const val publishedAt = "publishedAt"
            const val content = "content"

            const val sourceId = "id"
            const val sourceName = "name"
        }
    }
}
