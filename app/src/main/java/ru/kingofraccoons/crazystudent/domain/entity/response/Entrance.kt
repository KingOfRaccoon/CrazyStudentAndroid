package ru.kingofraccons.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entrance(
    @SerialName("color")
    val color: String,
    @SerialName("exit")
    val exit: Boolean,
    @SerialName("exit_comment")
    val exitComment: String,
    @SerialName("geometry")
    val geometry: String,
    @SerialName("metro_logo")
    val metroLogo: String,
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: String = "",
    @SerialName("show_other_station")
    val showOtherStation: Boolean
)