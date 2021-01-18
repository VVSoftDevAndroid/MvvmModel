package com.vvsoftdev.mvvmmodel.api

import com.vvsoftdev.mvvmmodel.data.DogSubBreed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("/api/breed/{breed}/list")
    suspend fun getSubBreeds(@Path(value = "breed") breed: String): Response<DogSubBreed>
}