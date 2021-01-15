package com.vvsoftdev.mvvmmodel.viewmodel

import androidx.lifecycle.*
import com.vvsoftdev.mvvmmodel.repository.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val dogSubBreedsFetchingLiveData = MutableLiveData<List<String>>(listOf())
    val dogSubBreedsListLiveData: LiveData<List<String>> = dogSubBreedsFetchingLiveData

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    private val _breedNameLiveData: MutableLiveData<String> = MutableLiveData()
    val breedNameLiveData: LiveData<String> get() = _breedNameLiveData

    init {
        viewModelScope.launch {
            _breedNameLiveData.postValue("hound")
            _isLoading.postValue(true)
            loadSubBreedsOf("hound")
        }
    }

    private suspend fun loadSubBreedsOf(breed: String) {
        mainRepository.loadSubBreedsList(
            breed,
            onSuccess = { _isLoading.postValue(false) },
            onError = { _toastLiveData.postValue(it) }
        ).collect { value -> dogSubBreedsFetchingLiveData.value = value }
    }

    fun onClickBreedName() {
        viewModelScope.launch {
            _breedNameLiveData.postValue("retriever")
            _isLoading.postValue(true)
            loadSubBreedsOf("retriever")
        }
    }

}


