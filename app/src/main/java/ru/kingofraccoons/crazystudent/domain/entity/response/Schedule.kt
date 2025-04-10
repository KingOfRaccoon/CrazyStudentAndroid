package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val period: Long? = null,
    val preciseTime: String? = null,
    val startTime: Int? = null,
    val startTimeUtc: Int? = null,
    val type: String
)
