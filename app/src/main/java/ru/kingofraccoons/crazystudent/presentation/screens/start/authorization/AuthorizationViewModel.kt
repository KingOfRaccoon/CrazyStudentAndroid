package ru.kingofraccoons.crazystudent.presentation.screens.start.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.domain.entity.request.LoginRequest
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository

class AuthorizationViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    val user = userRepository.userFlow
    val login = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun loginUser() {
        viewModelScope.launch {
            userRepository.login(
                LoginRequest(
                    login.value,
                    password.value
                )
            )
        }
    }
}