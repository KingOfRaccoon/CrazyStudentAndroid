package ru.kingofraccoons.crazystudent.domain.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class EventUpdate(
    val id: Long,
    val planId: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val timeStart: String? = null,
    val timeEnd: String? = null,
    val pointPlaceId: Long? = null
)
