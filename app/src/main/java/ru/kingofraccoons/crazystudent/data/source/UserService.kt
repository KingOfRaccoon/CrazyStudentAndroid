package ru.kingofraccoons.crazystudent.data.source

import io.ktor.http.HttpHeaders
import ru.kingofraccoons.crazystudent.data.util.Postman
import ru.kingofraccoons.crazystudent.domain.entity.request.LoginRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.RegistrationUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.UpdateUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.response.userdata.User
import ru.kingofraccoons.crazystudent.domain.util.Resource

class UserService(private val postman: Postman) {
    private val baseUrl = "89.223.121.212:8084/"
    private val loginTag = "login"
    private val loginOnTokenTag = "loginOnToken"
    private val registrationTag = "registration"
    private val updateUserTag = "updateUser"

    suspend fun login(loginRequest: LoginRequest): Resource<User> {
        return postman.post(baseUrl, loginTag, loginRequest)
    }

    suspend fun loginOnToken(token: String): Resource<User> {
        return postman.post(
            baseUrl,
            loginOnTokenTag,
            null,
            mapOf(HttpHeaders.Authorization to token)
        )
    }

    suspend fun registration(registrationRequest: RegistrationUserRequest): Resource<User> {
        return postman.post(baseUrl, registrationTag, registrationRequest)
    }

    suspend fun updateUser(updateUserRequest: UpdateUserRequest): Resource<User> {
        return postman.put(baseUrl, updateUserTag, updateUserRequest)
    }
}