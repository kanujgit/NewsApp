package com.flatworld.newsapp.news.model

/**
 * Describes all the events originated from
 * [NewsArticlesAdapter].
 */
sealed class NewsAdapterEvent() {

    /* Describes item click event  */
    data class ClickItemDetail(val newsArticle: NewsArticle) : NewsAdapterEvent()

    object ClickEvent : NewsAdapterEvent()

}