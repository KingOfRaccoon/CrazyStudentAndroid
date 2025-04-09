package ru.kingofraccoons.crazystudent.domain.entity.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Waypoint(
    @SerialName("combined")
    val combined: Boolean = false,
    @SerialName("comment")
    val comment: String,
    @SerialName("name")
    val name: String,
    @SerialName("subtype")
    val subtype: String
)