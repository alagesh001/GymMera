package com.example.gym.container

import android.content.Context
import com.example.gym.network.GymApiService
import com.example.gym.repository.GymApiRepository
import com.example.gym.repository.NetworkGymApiRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val gymApiRepository: GymApiRepository
}


class GymAppContainer(private val context: Context) : AppContainer {

    private val baseUrl =
        "https://greenyellow-octopus-821386.hostingersite.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: GymApiService by lazy {
        retrofit.create(GymApiService::class.java)
    }

    override val gymApiRepository: GymApiRepository by lazy {
        NetworkGymApiRepository(retrofitService)
    }
}