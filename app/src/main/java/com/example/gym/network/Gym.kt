package com.example.gym.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gym(
    @SerialName("Id")
    val id: Int,
    @SerialName("GymName")
    val gymName: String,
    @SerialName("Location")
    val location: String,
    @SerialName("GymId")
    val gymId: Int,
    @SerialName("membercount")
    val memberCount: Int
)