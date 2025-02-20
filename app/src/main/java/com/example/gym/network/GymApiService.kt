package com.example.gym.network

import retrofit2.http.GET

interface GymApiService {
    @GET("getgymdata?mobile=8148602102&password=ags123")
    suspend fun getData(): GymResponse
}