package com.flatworld.newsapp.news.ui.homedrawer.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.flatworld.newsapp.core.extensions.toast
import com.flatworld.newsapp.databinding.BookmarkFragmentBinding
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.storage.entity.NewsArticleDb
import com.flatworld.newsapp.news.ui.homedrawer.HomeDrawerActivity
import com.flatworld.newsapp.news.ui.homedrawer.category.viewmodel.CommonCategoryViewModel
import timber.log.Timber

class BookmarkFragment : Fragment(), BookmarkNewsArticlesAdapter.OnItemClickListener {


    private var _binding: BookmarkFragmentBinding? = null
    private lateinit var adapter: BookmarkNewsArticlesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val viewModel: CommonCategoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)

        // Setting up RecyclerView and adapter
        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)

        adapter = BookmarkNewsArticlesAdapter(ArrayList()) { toast("item clicked") }
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = GridLayoutManager(activity, 2)
        adapter.setOnItemClickListener(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Timber.d("start")
        viewModel.getAllBookMarks()
            .observe(viewLifecycleOwner, {
                Timber.d("emitted data : $it ")
                if (it.equals("Loading")) {
                    binding.emptyLayout.emptyView.visibility = View.GONE
                    (activity as HomeDrawerActivity?)?.showProgressBar()

                } else if (it.equals("Error")) {
                    binding.emptyLayout.emptyView.visibility = View.VISIBLE
                    (activity as HomeDrawerActivity?)?.hideProgressBar()

                } else {
                    binding.newsList.adapter = adapter
                    adapter.setData(it as ArrayList<NewsArticleDb>)
                    binding.newsList.visibility = View.VISIBLE
                    binding.emptyLayout.emptyView.visibility = View.GONE
                    (activity as HomeDrawerActivity?)?.hideProgressBar()
                }

            })
    }

    // make compatible to newsArticleDatabase class
    private fun NewsArticleDb.toNewsArticle() = NewsArticle(
        source = NewsArticle.Source(id = source.id, name = source.name),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )

    override fun onItemClick(item: NewsArticleDb) {
        viewModel.selectItem(item = item.toNewsArticle())
    }


}