package ru.kingofraccoons.crazystudent.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kingofraccoons.crazystudent.data.network.TimetableService
import ru.kingofraccoons.crazystudent.domain.entity.request.EventRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.EventUpdate
import ru.kingofraccoons.crazystudent.domain.entity.response.PlanResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.TimetableResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Group
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Teacher
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.util.Resource

class TimetableRepositoryImpl(private val timetableService: TimetableService) :
    TimetableRepository {
    private val _planFlow = MutableStateFlow<Resource<PlanResponse>>(Resource.Loading())
    override val planFlow = _planFlow.asStateFlow()
    override val planCache = hashMapOf<String, Resource<PlanResponse>>()

    private val _timetableFlow = MutableStateFlow<Resource<TimetableResponse>>(Resource.Loading())
    override val timetableFlow = _timetableFlow.asStateFlow()
    override val timetableCache = hashMapOf<String, Resource<TimetableResponse>>()

    private val _groupsFlow = MutableStateFlow<Resource<List<Group>>>(Resource.Loading())
    override val groupsFlow = _groupsFlow.asStateFlow()

    private val _teachersFlow = MutableStateFlow<Resource<List<Teacher>>>(Resource.Loading())
    override val teachersFlow = _teachersFlow.asStateFlow()

    override suspend fun loadPlan(userId: Long, date: String) {
        _planFlow.update {
            planCache[date].let {
                if (it != null && it is Resource.Success)
                    it
                else
                    return@let timetableService.getPlan(userId, date).also {
                        if (it is Resource.Success)
                            planCache[date] = it
                    }
            }
        }
    }

    override suspend fun loadGroups() {
        _groupsFlow.update {
            timetableService.getGroups()
        }
    }

    override suspend fun loadTeachers() {
        _teachersFlow.update {
            timetableService.getTeachers()
        }
    }

    override suspend fun loadTimetable(userId: Long, date: String) {
        _timetableFlow.update {
            timetableCache[date].let {
                val newTimetable = timetableService.getTimetable(userId, date)
                return@let if (it != null && it is Resource.Success
                    && it.data.getUnits() == newTimetable.data?.getUnits()
                )
                    it
                else
                    newTimetable.also {
                        if (it is Resource.Success)
                            timetableCache[date] = it
                    }
            }
        }
    }

    override suspend fun addEvents(
        userId: Long,
        eventRequest: EventRequest,
    ): Resource<String> {
        return timetableService.addEvents(userId, eventRequest)
    }

    override suspend fun updateEvent(eventUpdate: EventUpdate): Resource<String> {
        return timetableService.updateEvent(eventUpdate)
    }
}