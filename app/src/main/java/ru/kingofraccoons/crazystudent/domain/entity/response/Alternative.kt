package ru.kingofraccons.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.kingofraccoons.crazystudent.domain.entity.response.Platform
import ru.kingofraccoons.crazystudent.domain.entity.response.Route

@Serializable
data class Alternative(
    @SerialName("entrances")
    val entrances: List<Entrance> = listOf(),
    @SerialName("geometry")
    val geometry: List<Geometry>,
    @SerialName("platforms")
    val platforms: List<Platform> = listOf(),
    @SerialName("routes")
    val routes: List<Route> = listOf()
)