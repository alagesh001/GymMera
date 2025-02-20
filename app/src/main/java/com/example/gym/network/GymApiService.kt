package com.example.gym.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GymApiService {
    @GET("getgymdata") // Endpoint without query parameters
    suspend fun getData(
        @Query("mobile") mobile: String, // Dynamic mobile parameter
        @Query("password") password: String // Dynamic password parameter
    ): GymResponse
}