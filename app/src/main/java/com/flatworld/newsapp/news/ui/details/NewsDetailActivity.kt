package com.flatworld.newsapp.news.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.flatworld.newsapp.R
import com.flatworld.newsapp.databinding.ActivityNewsDetailBinding
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.utils.DateUtil

class NewsDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsDetailBinding
    lateinit var viewModel: NewsDetailViewModel
    lateinit var newsArticle: NewsArticle
    lateinit var item: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // update ui component
        newsArticle = intent.getParcelableExtra("data")!!
        updateUiComponents(newsArticle)

        // init view model
        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)


    }

    private fun updateUiComponents(newsArticle: NewsArticle) {
        binding.contentDetail.imgNews.load(newsArticle.urlToImage) {
            placeholder(R.drawable.news_placeholder)
            error(R.drawable.news_placeholder)
        }
        binding.contentDetail.tvTitleText.text = newsArticle.title
        binding.contentDetail.tvSubTitle.text = newsArticle.description

        binding.contentDetail.tvUpdateDate.text =
            DateUtil.getTimeDifference(DateUtil.formatDate(newsArticle.publishedAt))
        saveAndUpdateBookMark()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.detail_view_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.item = item
        return when (item.itemId) {
            R.id.action_bookmark -> {
                saveAndUpdateBookMark()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndUpdateBookMark() {
        if (this::item.isInitialized) {
            viewModel.isArticleSaved(newsArticle).observe(this, Observer {
                if (it != null) {
                    if (it.equals("saved")) {
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_fill_star)
                    } else if (it.equals("clear")) {
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
                    }
                }
            })
        }

    }
}