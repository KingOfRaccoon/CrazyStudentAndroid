package ru.kingofraccoons.crazystudent.domain.entity.response.timetable

import kotlinx.serialization.Serializable

@Serializable
data class Group(
	val id: Int,
	val aisId: Int,
	val title: String? = null
)
