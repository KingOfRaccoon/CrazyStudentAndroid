package ru.kingofraccoons.crazystudent.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.kingofraccoons.crazystudent.domain.entity.request.EventRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.EventUpdate
import ru.kingofraccoons.crazystudent.domain.entity.response.PlanResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.TimetableResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Group
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Teacher
import ru.kingofraccoons.crazystudent.domain.util.Resource

interface TimetableRepository {
    val planFlow: StateFlow<Resource<PlanResponse>>
    val planCache: HashMap<String, Resource<PlanResponse>>

    val timetableFlow: StateFlow<Resource<TimetableResponse>>
    val timetableCache: HashMap<String, Resource<TimetableResponse>>

    val groupsFlow: StateFlow<Resource<List<Group>>>
    val teachersFlow: StateFlow<Resource<List<Teacher>>>

    suspend fun loadPlan(userId: Long, date: String)
    suspend fun loadTimetable(userId: Long, date: String)

    suspend fun loadGroups()
    suspend fun loadTeachers()

    suspend fun addEvents(userId: Long, eventRequest: EventRequest): Resource<String>
    suspend fun updateEvent(eventUpdate: EventUpdate): Resource<String>
}