package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Route(
    @SerialName("crossing_count")
    val crossingCount: Int,
    @SerialName("id")
    val id: String,
    @SerialName("movements")
    val movements: List<Movement>,
    @SerialName("pedestrian")
    val pedestrian: Boolean,
    @SerialName("route_id")
    val routeId: String,
    @SerialName("schedules")
    val schedules: List<Schedule> = listOf(),
    @SerialName("total_duration")
    val totalDuration: Int,
    @SerialName("total_walkway_distance")
    val totalWalkwayDistance: String,
    @SerialName("transfer_count")
    val transferCount: Int,
    @SerialName("transport")
    val transport: List<String> = listOf(),
    @SerialName("waypoints")
    val waypoints: List<WaypointX> = listOf()
)