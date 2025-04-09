package ru.kingofraccoons.crazystudent.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class PointPlace(
    val id: Long,
    val address: String,
    val name: String,
    val fullName: String,
    val building: String,
    val latitude: Double,
    val longitude: Double,
)