package com.vvsoftdev.mvvmmodel.ui.adapter


import android.view.View
import com.vvsoftdev.mvvmmodel.R
import com.vvsoftdev.mvvmmodel.base.BaseAdapter
import com.vvsoftdev.mvvmmodel.base.SectionRow
import com.vvsoftdev.mvvmmodel.ui.viewholder.DogBreedViewHolder

class DogBreedAdapter: BaseAdapter() {

    init {
        addSection(arrayListOf<String>())
    }

    fun addDogBreedList(breeds: List<String>) {
        sections().first().run {
            clear()
            addAll(breeds)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow) = R.layout.item_breed

    override fun viewHolder(layout: Int, view: View) = DogBreedViewHolder(view)

}