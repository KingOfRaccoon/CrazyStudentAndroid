package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Serializable
data class RoadTimeResponse(
    override val timeStart: String,
    override val timeEnd: String,
    override val place: PointPlace,
    val endPlace: PointPlace,
    var route: List<RouteResponse> = listOf(),
    var startSuggestPlaces: Map<String, List<PlaceResponse>?> = mapOf(),
    var endSuggestPlaces: Map<String, List<PlaceResponse>?> = mapOf()
): TimetableUnitResponse