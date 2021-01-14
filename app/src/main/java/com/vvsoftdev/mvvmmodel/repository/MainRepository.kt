package com.vvsoftdev.mvvmmodel.repository

import androidx.annotation.WorkerThread
import com.vvsoftdev.mvvmmodel.api.DogApi
import com.vvsoftdev.mvvmmodel.data.DogBreed
import com.vvsoftdev.mvvmmodel.data.DogSubBreed
import com.vvsoftdev.mvvmmodel.data.getBreedsName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//todo add Room DAO for local storage
class MainRepository constructor(
    private val dogApi: DogApi
) : Repository {

    @WorkerThread
    suspend fun loadSubBreedsList(
        breed: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        // request API network call asynchronously.
        dogApi.getHoundSubBreeds(breed).apply {
            val response = this
            val data = response.body()
            // handle the case when the API request gets a success response.
            if (response.isSuccessful && data != null) {
                if (data.message.isNotEmpty()) emit(data.message) else onError("Pas de sous races disponibles pour les $breed")
                onSuccess()
            } else {
                onError(response.errorBody().toString())
            }
        }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun loadAllBreedsList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        //if local storage is empty, get breeds from rest API
        // request API network call asynchronously.
        dogApi.getAllBreedsList().apply {
            val response = this
            val data = response.body()
            // handle the case when the API request gets a success response.
            if (response.isSuccessful && data != null) {
                //todo insert in room db here -> dogDao.insertBreedList(data)
                emit(data)
                onSuccess()
            } else {
                onError(response.errorBody().toString())
            }
        }
    }.flowOn(Dispatchers.IO)

}