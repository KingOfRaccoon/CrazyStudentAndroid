package ru.kingofraccoons.crazystudent.presentation.screens.start.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository

class SplashViewModel(private val userRepository: UserRepository): ViewModel() {
    val userFlow = userRepository.userFlow.map { it to userRepository.getToken() }

    fun loginOnToken() {
        viewModelScope.launch {
            userRepository.loginOnToken()
        }
    }
}