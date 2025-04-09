package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class PlanResponse(
    val id: Long,
    val userId: Long,
    val date: String,
    val units: List<TimetableUnitResponse>
)