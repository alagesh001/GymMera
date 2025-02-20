package com.example.gym.repository

import com.example.gym.network.GymResponse
import com.example.gym.network.GymApiService

interface GymApiRepository {
    suspend fun getGymData(): GymResponse
}



class NetworkGymApiRepository(
    private val gymApiService: GymApiService
): GymApiRepository {
    override suspend fun getGymData(): GymResponse = gymApiService.getData()
}