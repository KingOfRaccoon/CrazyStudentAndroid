package ru.kingofraccoons.crazystudent.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class EventRequest(
    val name: String,
    val description: String,
    val timeStart: String,
    val timeEnd: String,
    val address: String
)
