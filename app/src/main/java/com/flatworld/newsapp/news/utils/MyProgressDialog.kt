package com.flatworld.newsapp.news.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout


class MyProgressDialog {
    var dialog: Dialog? = null
    private var progressBar: ProgressBar? = null

    fun myProgressDialog(context: Context?) {
        dialog = Dialog(context!!)
        dialog!!.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        val relativeLayout = RelativeLayout(context)
        val layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        relativeLayout.layoutParams = layoutParams
        progressBar = ProgressBar(context)
        val layoutParamsProgress = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParamsProgress.addRule(RelativeLayout.CENTER_IN_PARENT)
        val linearlayoutParamsProgress = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        linearlayoutParamsProgress.gravity = Gravity.CENTER
        progressBar!!.layoutParams = layoutParamsProgress
        relativeLayout.addView(progressBar)
        dialog!!.window?.setContentView(relativeLayout, layoutParams)
        dialog!!.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }

    fun show() {
        if (!dialog!!.isShowing && dialog != null) {
            dialog!!.show()
        }
    }

    fun dismiss() {
        if (dialog!!.isShowing && dialog != null) {
            dialog!!.dismiss()
        }
    }

    fun setCancelable(cancelable: Boolean) {
        dialog?.setCancelable(cancelable)
    }


    fun setCanceledOnTouchOutside(flag: Boolean) {
        dialog?.setCanceledOnTouchOutside(flag)
    }

    fun setColor(colour: Int) {
        progressBar!!.indeterminateDrawable.setColorFilter(colour, PorterDuff.Mode.MULTIPLY)
    }

    fun isShowing(): Boolean {
        return dialog?.isShowing()!!
    }
}