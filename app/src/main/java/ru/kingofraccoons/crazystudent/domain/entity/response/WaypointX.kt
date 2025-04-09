package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WaypointX(
    @SerialName("combined")
    val combined: Boolean,
    @SerialName("routes_names")
    val routesNames: List<String>,
    @SerialName("subtype")
    val subtype: String
)