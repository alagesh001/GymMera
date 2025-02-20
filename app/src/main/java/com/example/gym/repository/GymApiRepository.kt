package com.example.gym.repository

import com.example.gym.network.GymResponse
import com.example.gym.network.GymApiService

interface GymApiRepository {
    suspend fun getGymData(mobile: String, password: String): GymResponse
}


class NetworkGymApiRepository(
    private val gymApiService: GymApiService
) : GymApiRepository {
    override suspend fun getGymData(mobile: String, password: String): GymResponse =
        gymApiService.getData(mobile, password)
}