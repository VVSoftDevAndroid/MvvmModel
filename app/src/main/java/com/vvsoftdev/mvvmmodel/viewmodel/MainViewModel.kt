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

    private var dogSubBreedsFetchingLiveData = MutableLiveData(true)
    val dogSubBreedsListLiveData: LiveData<List<String>>

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    private val _breedNameLiveData: MutableLiveData<String> = MutableLiveData()
    val breedNameLiveData: LiveData<String> get() = _breedNameLiveData

    init {
        dogSubBreedsListLiveData =
            dogSubBreedsFetchingLiveData.switchMap {
                _breedNameLiveData.postValue("hound")
                _isLoading.postValue(true)
                showSubBreedsOf("hound")
            }
    }

    private fun showSubBreedsOf(breed: String): LiveData<List<String>> =
        launchOnViewModelScope {
            mainRepository.loadSubBreedsList(
                breed,
                onSuccess = { _isLoading.postValue(false) },
                onError = { _toastLiveData.postValue(it) }
            ).asLiveData()
        }

    fun onClickBreedName() {
        _breedNameLiveData.postValue("retriever")
        _isLoading.postValue(true)
        showSubBreedsOf("retriever")
    }

}


