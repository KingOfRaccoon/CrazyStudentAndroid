package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Serializable
data class LessonResponse(
    val build: String,
    val day: Int,
    val dept: String? = null,
    val disc: String,
    val groups: String,
    val groupsText: String,
    val itemId: Int,
    val less: Int,
    val preps: String?,
    val prepsText: String,
    val rooms: String,
    val typeLesson: String,
    val week: Int,
    override val timeStart: String, 
    override val timeEnd: String,
    override val place: PointPlace
): TimetableUnitResponse