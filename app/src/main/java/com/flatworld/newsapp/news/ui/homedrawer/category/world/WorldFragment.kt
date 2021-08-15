package com.flatworld.newsapp.news.ui.homedrawer.category.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.flatworld.newsapp.core.extensions.toast
import com.flatworld.newsapp.databinding.WorldFragmentBinding
import com.flatworld.newsapp.news.model.NewsArticle
import com.flatworld.newsapp.news.ui.homedrawer.adapter.NewsArticlesAdapter
import timber.log.Timber


class WorldFragment : Fragment() {

    private var _binding: WorldFragmentBinding? = null
    private lateinit var adapter: NewsArticlesAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = WorldFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WorldFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // provide fragment as a ViewStoreOwner
        val viewModel = ViewModelProvider(this).get(WorldViewModel::class.java)

        // init and set repo class
        val repo = WorldRepository()
        viewModel.setRepo(repo)

        // Setting up RecyclerView and adapter
        binding.newsList.setEmptyView(binding.emptyLayout.emptyView)
        binding.newsList.setProgressView(binding.progressLayout.progressView)

        adapter = NewsArticlesAdapter(ArrayList()) { toast("item clicked") }
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = GridLayoutManager(activity, 2)


        viewModel.fetchWorldHeadlines()
        viewModel.fetchWorldHeadlines().observe(viewLifecycleOwner, Observer {
            Timber.d("emitted data : $it ")
            adapter.setData(it as ArrayList<NewsArticle>)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}