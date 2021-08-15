package com.flatworld.newsapp.news.ui.homedrawer.category.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.flatworld.newsapp.R

class WorldFragment : Fragment() {

    companion object {
        fun newInstance() = WorldFragment()
    }

    private lateinit var viewModel: WorldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.world_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey("ARG_OBJECT") }?.apply {
            val textView: TextView = view.findViewById(R.id.text)
            textView.text = getInt("ARG_OBJECT").toString()
        }

    }

}