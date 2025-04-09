package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class RouteResponse(
    val crossingCount: Int,
    val id: String,
    val movements: List<MovementResponse>,
    val pedestrian: Boolean,
    val routeId: String,
    val schedules: List<Schedule> = listOf(),
    val totalDuration: Int,
    val totalWalkwayDistance: String,
    val transferCount: Int,
    val transport: List<String> = listOf(),
    val waypoints: List<WaypointX> = listOf()
)