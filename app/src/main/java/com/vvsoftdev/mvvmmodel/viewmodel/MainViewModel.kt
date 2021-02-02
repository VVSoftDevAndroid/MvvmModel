package com.vvsoftdev.mvvmmodel.viewmodel

import androidx.lifecycle.*
import com.vvsoftdev.mvvmmodel.repository.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dogSubBreedsLiveData = MutableLiveData<List<String>>(listOf())
    val dogSubBreedsListLiveData: LiveData<List<String>> = _dogSubBreedsLiveData

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
        ).collect { value -> _dogSubBreedsLiveData.value = value }
    }

    fun onLoadNewBreed() {
        if (_breedNameLiveData.value.equals("hound"))
            _breedNameLiveData.postValue("retriever")
        else
            _breedNameLiveData.postValue("hound")
    }

    fun onClickBreedName(breed: String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            loadSubBreedsOf(breed)
        }
    }

}


