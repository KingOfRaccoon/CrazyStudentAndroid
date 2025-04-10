package ru.kingofraccoons.crazystudent.presentation.screens.main.profile.student

import androidx.lifecycle.ViewModel
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {
    val user = userRepository.userFlow
}