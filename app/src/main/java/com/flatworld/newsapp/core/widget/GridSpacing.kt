package com.flatworld.newsapp.core.widget

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView


class GridSpacing(spacing: Int) :RecyclerView.ItemDecoration() {
    private val spacing = 0
    override
    fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.left = spacing
        outRect.right = spacing
        outRect.bottom = spacing
        outRect.top = spacing
    }
}