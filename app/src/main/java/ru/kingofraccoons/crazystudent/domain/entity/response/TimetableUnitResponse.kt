package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.Polymorphic
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Polymorphic
interface TimetableUnitResponse {
    val timeStart: String
    val timeEnd: String
    val place: PointPlace
}