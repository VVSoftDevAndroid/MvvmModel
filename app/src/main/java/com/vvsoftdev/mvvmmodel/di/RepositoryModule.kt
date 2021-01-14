package com.vvsoftdev.mvvmmodel.di

import com.vvsoftdev.mvvmmodel.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { MainRepository(get()) }

}