package ru.kingofraccons.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerialName("selection")
    val selection: String,
    @SerialName("z_first")
    val zFirst: Int = 0,
    @SerialName("z_last")
    val zLast: Int = 0
)