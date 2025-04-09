package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Platform(
    @SerialName("geometry")
    val geometry: String,
    @SerialName("id")
    val id: String
)