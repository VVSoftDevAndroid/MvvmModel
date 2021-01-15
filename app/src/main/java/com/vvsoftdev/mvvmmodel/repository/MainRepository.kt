package com.vvsoftdev.mvvmmodel.repository

import com.vvsoftdev.mvvmmodel.api.DogApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//todo add Room DAO for local storage
class MainRepository constructor(
    private val dogApi: DogApi
) : Repository {

    suspend fun loadSubBreedsList(
        breed: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ): Flow<List<String>> = flow {
        // request API network call asynchronously.
        dogApi.getSubBreeds(breed).apply {
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
}