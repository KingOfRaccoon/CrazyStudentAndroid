package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Platforms(
    @SerialName("names")
    val names: List<String> = listOf(),
    @SerialName("penultimate_stop")
    val penultimateStop: String = ""
)