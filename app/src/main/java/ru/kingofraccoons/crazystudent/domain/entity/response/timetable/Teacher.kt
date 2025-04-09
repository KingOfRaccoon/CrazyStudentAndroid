package ru.kingofraccoons.crazystudent.domain.entity.response.timetable

import kotlinx.serialization.Serializable

@Serializable
data class Teacher(
	val id: Int,
	val aisId: Int,
	val fio: String? = null,
	val degree: String? = null
): FilterUnit {
	override fun getFilter() = fio ?: "id: $id"

	override fun getFilterForServer() = "prep$aisId"
}
