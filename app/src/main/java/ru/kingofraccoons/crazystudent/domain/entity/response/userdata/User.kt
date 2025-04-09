package ru.kingofraccoons.crazystudent.domain.entity.response.userdata

import kotlinx.serialization.Serializable
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Serializable
data class User(
    val id: Long,
    val homeLocation: PointPlace,
    val name: String,
    val lastname: String,
    val timetableUniversityFilter: String,
    val token: String
)