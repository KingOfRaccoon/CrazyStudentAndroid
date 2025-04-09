package ru.kingofraccoons.crazystudent.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.kingofraccoons.crazystudent.domain.entity.request.LoginRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.RegistrationUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.UpdateUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.response.userdata.User
import ru.kingofraccoons.crazystudent.domain.util.Resource

interface UserRepository {
    val userFlow: StateFlow<Resource<User>>

    suspend fun login(loginRequest: LoginRequest)
    suspend fun loginOnToken()
    suspend fun registration(registrationRequest: RegistrationUserRequest)
    suspend fun updateUser(updateUserRequest: UpdateUserRequest)
    fun getToken(): String?
}