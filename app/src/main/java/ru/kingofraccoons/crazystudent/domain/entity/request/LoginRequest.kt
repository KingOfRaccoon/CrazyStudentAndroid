package ru.kingofraccoons.crazystudent.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val login: String, val password: String)
