package ru.kingofraccoons.crazystudent.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.kingofraccoons.crazystudent.domain.entity.request.EventRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.EventUpdate
import ru.kingofraccoons.crazystudent.domain.entity.response.PlanResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Group
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Teacher
import ru.kingofraccoons.crazystudent.domain.util.Resource

interface TimetableRepository {
    val timetableFlow: StateFlow<Resource<PlanResponse>>
    val groupsFlow: StateFlow<Resource<List<Group>>>
    val teachersFlow: StateFlow<Resource<List<Teacher>>>

    suspend fun loadTimetable(userId: Int, date: String)
    suspend fun loadGroups()
    suspend fun loadTeachers()
    suspend fun loadEvents()
    suspend fun addEvents(userId: Int, eventRequest: EventRequest): Resource<String>
    suspend fun updateEvent(eventUpdate: EventUpdate): Resource<String>
}