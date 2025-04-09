package ru.kingofraccoons.crazystudent.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val address: String? = null,
    val name: String? = null,
    val lastname: String? = null,
    val timetableUniversityFilter: String? = null,
    val id: Long? = null,
    val homeLocationId: Long? = null,
)
