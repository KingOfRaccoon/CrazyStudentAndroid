package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.kingofraccons.models.Alternative

@Serializable
data class Movement(
    @SerialName("alternatives")
    val alternatives: List<Alternative> = listOf(),
    @SerialName("id")
    val id: String,
    @SerialName("metro")
    val metro: Metro? = null,
    @SerialName("platforms")
    val platforms: Platforms? = null,
    @SerialName("routes")
    val routes: List<Transport> = listOf(),
    @SerialName("type")
    val type: String,
    @SerialName("waypoint")
    val waypoint: Waypoint
)