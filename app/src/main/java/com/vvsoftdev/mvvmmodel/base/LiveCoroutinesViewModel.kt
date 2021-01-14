package com.vvsoftdev.mvvmmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout

abstract class LiveCoroutinesViewModel : ViewModel() {

    inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(300) { emitSource(block()) }
        }
}