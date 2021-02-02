package com.vvsoftdev.mvvmmodel.ui

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object ViewBinding {
    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean?) {
        if (shouldBeGone == true) {
            view.gone(true)
        } else {
            view.gone(false)
        }
    }

    @JvmStatic
    @BindingAdapter("text")
    fun bindTextBreed(view: TextView, text: String?) {
        if (text == "hound")
            "Change livedata to retriever".also { view.text = it }
        else
            "Change livedata to hound".also { view.text = it }
    }
}