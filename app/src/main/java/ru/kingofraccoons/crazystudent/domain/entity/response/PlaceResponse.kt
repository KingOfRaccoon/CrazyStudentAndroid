package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    val addressComment: String = "",
    val addressName: String = "",
    val buildingName: String = "",
    val fullName: String = "",
    val id: String,
    val name: String,
    val point: Point,
    val purposeName: String = "",
    val schedule: Map<String, List<String>> = emptyMap(),
    val type: String
)