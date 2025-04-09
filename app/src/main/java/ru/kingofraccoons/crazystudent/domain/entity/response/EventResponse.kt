package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Serializable
data class EventResponse(
    val id: Long,
    val planId: Long,
    val name: String,
    val description: String,
    override val timeStart: String,
    override val timeEnd: String,
    override val place: PointPlace
): TimetableUnitResponse