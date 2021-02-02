package com.vvsoftdev.mvvmmodel.ui.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vvsoftdev.mvvmmodel.base.RecyclerViewBaseAdapter
import com.vvsoftdev.mvvmmodel.ui.adapter.DogBreedAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, baseAdapter: RecyclerViewBaseAdapter) {
        view.adapter = baseAdapter
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: RecyclerView, text: String?) {
        text?.let {
            if (it.isNotEmpty())
                Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }

    }

    @JvmStatic
    @BindingAdapter("adapterSubBreedList")
    fun bindAdapterSubBreedList(view: RecyclerView, breeds: List<String>?) {
        if (!breeds.isNullOrEmpty()) {
            val adapter: DogBreedAdapter = view.adapter as DogBreedAdapter
            adapter.addDogBreedList(breeds)
        }
    }
}