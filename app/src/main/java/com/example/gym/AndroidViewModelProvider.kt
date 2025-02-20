package com.example.gym

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gym.viewmodel.GymViewModel

object AndroidViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            GymViewModel(
                gymApplication().container.gymApiRepository
            )
        }
    }
}






fun CreationExtras.gymApplication(): GymApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GymApplication)