package com.mis.example.amphibians.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// 1- DONE: Create a property for the base URL provided in the codelabe
private const val BASE_URL =
    "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

// 2- DONE: Build the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
private val moshi =
    Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

// 3- DONE: Build a Retrofit object with the Moshi converter
private val retrofit =
    Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

interface AmphibianApiService {
    // 4- DONE: Declare a suspended function to get the list of amphibians
    @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json")
    suspend fun getAmphibiansList(): List<Amphibian>
}

// 5- DONE: Create an object that provides a lazy-initialized retrofit service
object AmphibiansApi {
    val service: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }
}

