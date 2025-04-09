package ru.kingofraccoons.crazystudent.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kingofraccoons.crazystudent.data.network.UserService
import ru.kingofraccoons.crazystudent.data.storage.SharedPreferencesDataSource
import ru.kingofraccoons.crazystudent.domain.entity.request.LoginRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.RegistrationUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.UpdateUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.response.userdata.User
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository
import ru.kingofraccoons.crazystudent.domain.util.Resource

class UserRepositoryImpl(
    private val userService: UserService,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
) : UserRepository {
    private val _userFlow = MutableStateFlow<Resource<User>>(Resource.Loading())
    override val userFlow: StateFlow<Resource<User>> = _userFlow.asStateFlow()

    override suspend fun login(loginRequest: LoginRequest) {
        _userFlow.update { userService.login(loginRequest).also { it.saveToken() } }
    }

    override suspend fun loginOnToken() {
        val token = sharedPreferencesDataSource.getToken()
        if (token.isNotEmpty())
            _userFlow.update { userService.loginOnToken(token).also { it.saveToken() } }
    }

    override suspend fun registration(registrationRequest: RegistrationUserRequest) {
        _userFlow.update { userService.registration(registrationRequest).also { it.saveToken() } }
    }

    override suspend fun updateUser(updateUserRequest: UpdateUserRequest) {
        _userFlow.update { userService.updateUser(updateUserRequest).also { it.saveToken() } }
    }

    override fun getToken() = sharedPreferencesDataSource.getToken()

    fun Resource<User>.saveToken() {
        if (this is Resource.Success)
            sharedPreferencesDataSource.saveToken(this.data.token)
    }
}
