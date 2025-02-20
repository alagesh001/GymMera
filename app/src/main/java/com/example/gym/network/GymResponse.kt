package com.example.gym.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GymResponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("result")
    val result: List<Gym>
)