package com.flatworld.newsapp.news.ui.homedrawer.bookmark

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flatworld.newsapp.R
import com.flatworld.newsapp.core.extensions.inflate
import com.flatworld.newsapp.databinding.ItemViewNewsListBinding
import com.flatworld.newsapp.news.model.NewsAdapterEvent
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.storage.entity.NewsArticleDb
import com.flatworld.newsapp.news.utils.DateUtil

class BookmarkNewsArticlesAdapter(
    private var dataset: List<NewsArticleDb>,
    private val listener: (NewsAdapterEvent) -> Unit
) : RecyclerView.Adapter<BookmarkNewsArticlesAdapter.NewsHolder>(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (onItemClickListener != null) {
            if (v != null) {
                onItemClickListener!!.onItemClick(v.tag as NewsArticleDb)
            }

        }
    }

    // init click listener
    private var onItemClickListener: OnItemClickListener? = null


    // set click listener
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    /**
     * Inflate the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsHolder(parent.inflate(R.layout.item_view_news_list))

    /**
     * Bind the view with the data
     */
    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) {
        newsHolder.bind(dataset[position], listener, position)
        newsHolder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(dataset[position])
        }
    }


    /**
     * View Holder Pattern
     */
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemViewNewsListBinding.bind(itemView)

        /**
         * Binds the UI with the data and handles clicks
         */
        fun bind(newsArticle: NewsArticleDb, listener: (NewsAdapterEvent) -> Unit, position: Int) =
            with(itemView) {
                binding.newsTitle.text = newsArticle.title
                binding.newsAuthor.text = newsArticle.author
                binding.newsPublishedAt.text =
                    DateUtil.getTimeDifference(DateUtil.formatDate(newsArticle.publishedAt))
                binding.newsImage.load(newsArticle.urlToImage) {
                    placeholder(R.drawable.news_placeholder)
                    error(R.drawable.news_placeholder)
                }
                setOnClickListener {}
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


    // fix 100 data size
    //later implement pagination listener
    override fun getItemCount(): Int {
        return if (dataset.size > 100)
            100
        else dataset.size
        //return dataset.size
    }

    fun setData(dataset: List<NewsArticleDb>) {
        this.dataset = dataset
    }

    interface OnItemClickListener {
        fun onItemClick(viewModel: NewsArticleDb)
    }
}
