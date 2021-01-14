package com.vvsoftdev.mvvmmodel.di

import com.vvsoftdev.mvvmmodel.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

}