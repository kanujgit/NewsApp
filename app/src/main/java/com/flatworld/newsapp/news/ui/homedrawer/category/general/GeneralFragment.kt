package com.flatworld.newsapp.news.ui.homedrawer.category.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.flatworld.newsapp.core.extensions.toast
import com.flatworld.newsapp.databinding.GeneralFragmentBinding
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.ui.homedrawer.HomeDrawerActivity
import com.flatworld.newsapp.news.ui.homedrawer.adapter.NewsArticlesAdapter
import timber.log.Timber


class GeneralFragment : Fragment() {

    private var _binding: GeneralFragmentBinding? = null
    private lateinit var adapter: NewsArticlesAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GeneralFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // provide fragment as a ViewStoreOwner
        val viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)

        // init and set repo class
        val repo = GeneralRepo()
        viewModel.setRepo(repo)

        // Setting up RecyclerView and adapter
        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)

        adapter = NewsArticlesAdapter(ArrayList()) { toast("item clicked") }
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = GridLayoutManager(activity, 2)


        viewModel.fetchGeneralHeadlines()
        viewModel.fetchGeneralHeadlines().observe(viewLifecycleOwner, Observer {
            Timber.d("emitted data : $it ")
            if (it.equals("Loading")) {
                binding.emptyLayout.emptyView.visibility = View.GONE
                (activity as HomeDrawerActivity?)?.showProgressBar()

            } else if (it.equals("Error")) {
                binding.emptyLayout.emptyView.visibility = View.VISIBLE
                (activity as HomeDrawerActivity?)?.hideProgressBar()

            } else {
                adapter.setData(it as ArrayList<NewsArticle>)
                binding.newsList.visibility = View.VISIBLE
                binding.emptyLayout.emptyView.visibility = View.GONE
                (activity as HomeDrawerActivity?)?.hideProgressBar()
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}