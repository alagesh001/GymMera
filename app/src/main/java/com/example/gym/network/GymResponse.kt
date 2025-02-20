package com.example.gym.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GymResponse(
    val success: Boolean,
    val message: String? = null,  // Optional field (only present when success = false)
    val result: List<Gym>? = null // Optional field (only present when success = true)
)