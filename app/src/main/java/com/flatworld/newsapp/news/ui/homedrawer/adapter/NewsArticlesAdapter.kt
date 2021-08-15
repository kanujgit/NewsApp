package com.flatworld.newsapp.news.ui.homedrawer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flatworld.newsapp.BuildConfig
import com.flatworld.newsapp.R
import com.flatworld.newsapp.core.extensions.inflate
import com.flatworld.newsapp.databinding.ItemViewNewsListBinding
import com.flatworld.newsapp.news.model.NewsAdapterEvent
import com.flatworld.newsapp.news.model.NewsArticle

class NewsArticlesAdapter(private var dataset: List<NewsArticle>,
                          private val listener: (NewsAdapterEvent) -> Unit
) : RecyclerView.Adapter<NewsArticlesAdapter.NewsHolder>() {

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsHolder(parent.inflate(R.layout.item_view_news_list))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) =
        newsHolder.bind(dataset[position], listener)

    /**
     * View Holder Pattern
     */
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemViewNewsListBinding.bind(itemView)

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: NewsArticle, listener: (NewsAdapterEvent) -> Unit) = with(itemView) {
            binding.newsTitle.text = newsArticle.title
            binding.newsAuthor.text = newsArticle.author
            //TODO: need to format date
            //tvListItemDateTime.text = getFormattedDate(newsArticle.publishedAt)
            binding.newsPublishedAt.text = newsArticle.publishedAt
            binding.newsImage.load(BuildConfig.BASE_URL) {
                placeholder(R.drawable.news_placeholder)
                error(R.drawable.news_placeholder)
            }

            setOnClickListener { listener(NewsAdapterEvent.ClickEvent) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int = dataset.size

    fun setData(dataset: List<NewsArticle>) {
        this.dataset = dataset
    }
}
