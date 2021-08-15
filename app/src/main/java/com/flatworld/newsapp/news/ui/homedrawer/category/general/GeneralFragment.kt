package com.flatworld.newsapp.news.ui.homedrawer.category.general

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flatworld.newsapp.R

class GeneralFragment : Fragment() {

    companion object {
        fun newInstance() = GeneralFragment()
    }

    private lateinit var viewModel: GeneralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.general_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        // TODO: Use the ViewModel
    }

}