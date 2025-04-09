package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class MovementResponse(
    val id: String,
    val metro: Metro? = null,
    val platforms: Platforms? = null,
    val routes: List<Transport> = listOf(),
    val type: String,
    val waypoint: Waypoint
) {
    constructor(movement: Movement) : this(
        movement.id,
        movement.metro,
        movement.platforms,
        movement.routes,
        movement.type,
        movement.waypoint
    )
}
