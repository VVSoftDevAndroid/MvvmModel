package com.vvsoftdev.mvvmmodel

import android.app.Application
import com.vvsoftdev.mvvmmodel.di.networkModule
import com.vvsoftdev.mvvmmodel.di.repositoryModule
import com.vvsoftdev.mvvmmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }
}