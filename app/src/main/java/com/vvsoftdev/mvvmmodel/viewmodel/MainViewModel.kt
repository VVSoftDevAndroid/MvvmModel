package com.vvsoftdev.mvvmmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.vvsoftdev.mvvmmodel.base.LiveCoroutinesViewModel
import com.vvsoftdev.mvvmmodel.repository.MainRepository

class MainViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var dogHoundSubBreedsFetchingLiveData: MutableLiveData<Boolean> = MutableLiveData(true)
    val dogHoundSubBreedsListLiveData: LiveData<List<String>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    init {
        dogHoundSubBreedsListLiveData = dogHoundSubBreedsFetchingLiveData.switchMap {
            _isLoading.postValue(true)
            launchOnViewModelScope {
                this.mainRepository.loadSubBreedsList(
                    "african",
                    onSuccess = { _isLoading.postValue(false) },
                    onError = { _toastLiveData.postValue(it) }
                ).asLiveData()
            }
        }
    }

}
