package com.vvsoftdev.mvvmmodel.ui.viewholder

import android.view.View
import androidx.core.view.ViewCompat
import com.vvsoftdev.mvvmmodel.base.BaseViewHolder
import com.vvsoftdev.mvvmmodel.databinding.ItemBreedBinding
import com.vvsoftdev.mvvmmodel.viewmodel.bindings

class DogBreedViewHolder(view: View) : BaseViewHolder(view) {

    private lateinit var data: String
    private val binding: ItemBreedBinding by bindings(view)

    override fun bindData(data: Any) {
        if (data is String) {
            this.data = data
            drawItemUI()
        }
    }

    private fun drawItemUI() {
        binding.apply {
            ViewCompat.setTransitionName(binding.itemContainer, data)
            breed = data
            executePendingBindings()
        }
    }

    override fun onClick(p0: View?) {
        //todo
    }

    override fun onLongClick(p0: View?) = false
}