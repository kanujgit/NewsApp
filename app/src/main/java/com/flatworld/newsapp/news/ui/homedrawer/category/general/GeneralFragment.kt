package com.flatworld.newsapp.news.ui.homedrawer.category.general

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.flatworld.newsapp.R
import com.flatworld.newsapp.databinding.CommonCategoryViewBinding
import com.flatworld.newsapp.news.api.ResultType
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.repository.CommonRepo
import com.flatworld.newsapp.news.ui.homedrawer.HomeDrawerActivity
import com.flatworld.newsapp.news.ui.homedrawer.adapter.NewsArticlesAdapter
import com.flatworld.newsapp.news.ui.homedrawer.category.viewmodel.CommonCategoryViewModel


class GeneralFragment : Fragment(), NewsArticlesAdapter.OnItemClickListener {

    private var _binding: CommonCategoryViewBinding? = null
    private lateinit var adapter: NewsArticlesAdapter
    private val viewModel: CommonCategoryViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CommonCategoryViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(CommonCategoryViewModel::class.java)

        // init and set repo class
        val repo = CommonRepo()
        viewModel.setRepo(repo)

        // Setting up RecyclerView and adapter
        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)


        adapter = NewsArticlesAdapter(ArrayList()) {}

        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = GridLayoutManager(activity, 2)

        adapter.setOnItemClickListener(this@GeneralFragment)

        viewModel.fetchTopHeadlines(getString(R.string.ic_title_general))
            .observe(viewLifecycleOwner, Observer {
                when (it) {
                    is ResultType.Data -> {
                        adapter.setData(it.newsArticle.articles as ArrayList<NewsArticle>)
                        binding.newsList.visibility = View.VISIBLE
                        binding.emptyLayout.emptyView.visibility = View.GONE
                        (activity as HomeDrawerActivity?)?.hideProgressBar()
                    }
                    is ResultType.ErrorData -> {
                        binding.newsList.visibility = View.GONE
                        binding.emptyLayout.emptyView.visibility = View.VISIBLE
                        binding.emptyLayout.emptyTitle.text = it.error.message
                        (activity as HomeDrawerActivity?)?.hideProgressBar()
                    }
                    is ResultType.ErrorUnknown -> {
                        binding.newsList.visibility = View.GONE
                        binding.emptyLayout.emptyView.visibility = View.VISIBLE
                        binding.emptyLayout.emptyTitle.text = it.error
                        (activity as HomeDrawerActivity?)?.hideProgressBar()
                    }
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Called when the item is clicked
    override fun onItemClick(item: NewsArticle) {
        // Set a new item
        viewModel.selectItem(item = item)
    }
}