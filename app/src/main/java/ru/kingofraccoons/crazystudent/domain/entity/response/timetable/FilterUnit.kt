package ru.kingofraccoons.crazystudent.domain.entity.response.timetable

interface FilterUnit {
    fun getFilter(): String
    fun getFilterForServer(): String
}