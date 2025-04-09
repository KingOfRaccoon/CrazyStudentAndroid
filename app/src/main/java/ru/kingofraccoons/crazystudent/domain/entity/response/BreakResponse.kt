package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Serializable
data class BreakResponse(
    override val timeStart: String,
    override val timeEnd: String,
    override val place: PointPlace,
    val planId: Long = -1,
    val id: Long = -1,
    var suggestPlaces: Map<String, PlaceResponse?> = mapOf()
): TimetableUnitResponse