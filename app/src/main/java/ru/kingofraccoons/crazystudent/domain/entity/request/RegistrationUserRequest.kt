package ru.kingofraccoons.crazystudent.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationUserRequest(
    val address: String,
    val name: String,
    val lastname: String,
    val timetableUniversityFilter: String,
    val login: String,
    val password: String,
    val homeLocationId: Long? = null
)
