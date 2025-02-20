package com.example.gym.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gym.network.GymResponse
import com.example.gym.repository.GymApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GymViewModel(
    private val repository: GymApiRepository
) : ViewModel() {

    private val _gymResponseState = MutableStateFlow<GymResponse?>(null)
    val gymResponseState: StateFlow<GymResponse?> = _gymResponseState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchGymData()
    }

    private fun fetchGymData() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val response = repository.getGymData()
                _gymResponseState.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}
