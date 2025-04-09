package ru.kingofraccoons.crazystudent.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kingofraccoons.crazystudent.data.source.UserService
import ru.kingofraccoons.crazystudent.domain.entity.request.LoginRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.RegistrationUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.UpdateUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.response.userdata.User
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository
import ru.kingofraccoons.crazystudent.domain.util.Resource

class UserRepositoryImpl(private val userService: UserService): UserRepository {
    private val _userFlow = MutableStateFlow<Resource<User>>(Resource.Loading())
    override val userFlow: StateFlow<Resource<User>> = _userFlow.asStateFlow()

    override suspend fun login(loginRequest: LoginRequest) {
        _userFlow.update {
            userService.login(loginRequest)
        }
    }

    override suspend fun loginOnToken(token: String) {
        _userFlow.update {
            userService.loginOnToken(token)
        }
    }

    override suspend fun registration(registrationRequest: RegistrationUserRequest) {
        _userFlow.update {
            userService.registration(registrationRequest)
        }
    }

    override suspend fun updateUser(updateUserRequest: UpdateUserRequest) {
        _userFlow.update {
            userService.updateUser(updateUserRequest)
        }
    }
}