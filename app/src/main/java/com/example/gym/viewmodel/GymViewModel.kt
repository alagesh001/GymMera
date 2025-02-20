package com.example.gym.viewmodel

import android.util.Log
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

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess.asStateFlow()

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            _error.value = null
            _loginSuccess.value = false // Reset success before the request
            try {
                val response = repository.getGymData(mobile, password)
                Log.e("viewmodel", "response success: ${response.success}")
                if (response.success) {
                    _gymResponseState.value = response
                    _loginSuccess.value = true  // Mark login success
                    Log.e("viewmodel", "gymState success: ${_gymResponseState.value?.result?.joinToString(", ") { it.location }}")

                } else {
                    _error.value = "Invalid credentials"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            }
        }
    }

    // Reset the login success state
    fun resetLoginSuccess() {
        _loginSuccess.value = false
    }
}
