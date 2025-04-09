package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val period: Long? = null,
    val preciseTime: String,
    val startTime: Int,
    val startTimeUtc: Int,
    val type: String
)
