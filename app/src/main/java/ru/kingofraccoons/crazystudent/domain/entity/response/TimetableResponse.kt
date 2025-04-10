package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class TimetableResponse(val lessons: List<LessonResponse>, val events: List<EventResponse>) {
    fun getUnits() = lessons + events
}
