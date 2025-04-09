package ru.kingofraccoons.crazystudent.data.network

import ru.kingofraccoons.crazystudent.data.util.Postman
import ru.kingofraccoons.crazystudent.domain.entity.request.EventRequest
import ru.kingofraccoons.crazystudent.domain.entity.request.EventUpdate
import ru.kingofraccoons.crazystudent.domain.entity.response.EventResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.PlanResponse
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Group
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.Teacher
import ru.kingofraccoons.crazystudent.domain.util.Resource

class TimetableService(private val postman: Postman) {
    private val baseUrl = "http://89.223.121.212:8084/"
    private val timetableTag = "timetable"
    private val eventsTag = "events"

    private val timetableUrl = "https://api.guap.ru/rasp/v1/"
    private val groupTag = "get-groups"
    private val teacherTag = "get-preps"

    suspend fun getTimetable(userId: Int, date: String): Resource<PlanResponse> {
        return postman.get(
            baseUrl,
            timetableTag,
            arguments = mapOf("userId" to userId, "date" to date)
        )
    }

    suspend fun getGroups(): Resource<List<Group>> {
        return postman.get(timetableUrl, groupTag)
    }

    suspend fun getTeachers(): Resource<List<Teacher>> {
        return postman.get(timetableUrl, teacherTag)
    }

    suspend fun getEvents(): Resource<List<EventResponse>> {
        return postman.get(baseUrl, eventsTag)
    }

    suspend fun addEvents(userId: Int, eventRequest: EventRequest): Resource<String> {
        return postman.post(baseUrl, eventsTag, eventRequest, arguments = mapOf("userId" to userId))
    }

    suspend fun updateEvent(eventUpdate: EventUpdate): Resource<String> {
        return postman.put(baseUrl, eventsTag, eventUpdate)
    }
}