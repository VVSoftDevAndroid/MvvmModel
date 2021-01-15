package com.vvsoftdev.mvvmmodel.ui

import android.os.Bundle
import com.vvsoftdev.mvvmmodel.R
import com.vvsoftdev.mvvmmodel.base.DatabindingActivity
import com.vvsoftdev.mvvmmodel.databinding.ActivityMainBinding
import com.vvsoftdev.mvvmmodel.ui.adapter.DogBreedAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : DatabindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            breedListAdapter = DogBreedAdapter()
            lifecycleOwner = this@MainActivity
            viewmodel = getViewModel()
            executePendingBindings()
        }
    }
}