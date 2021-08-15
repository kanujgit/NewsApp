package com.flatworld.newsapp.news.ui.homedrawer.category.business

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flatworld.newsapp.R

class BusinessFragment : Fragment() {

    companion object {
        fun newInstance() = BusinessFragment()
    }

    private lateinit var viewModel: BusinessViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.business_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BusinessViewModel::class.java)
        // TODO: Use the ViewModel
    }

}