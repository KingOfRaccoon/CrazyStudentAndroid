package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transport(
    @SerialName("color")
    val color: String,
    @SerialName("names")
    val names: List<String>,
    @SerialName("subtype")
    val subtype: String,
    @SerialName("subtype_name")
    val subtypeName: String
)