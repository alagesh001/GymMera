package com.example.gym

import android.app.Application
import com.example.gym.container.AppContainer
import com.example.gym.container.GymAppContainer

class GymApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = GymAppContainer(this)
    }
}