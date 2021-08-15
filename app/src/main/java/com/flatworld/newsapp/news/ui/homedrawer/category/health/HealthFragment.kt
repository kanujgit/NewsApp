package com.flatworld.newsapp.news.ui.homedrawer.category.health

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flatworld.newsapp.R

class HealthFragment : Fragment() {

    companion object {
        fun newInstance() = HealthFragment()
    }

    private lateinit var viewModel: HealthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HealthViewModel::class.java)
        // TODO: Use the ViewModel
    }

}