package ru.kingofraccoons.crazystudent.domain.usecase

import ru.kingofraccoons.crazystudent.domain.entity.request.EventRequest
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository
import ru.kingofraccoons.crazystudent.domain.util.Resource

class AddEventUseCase(
    private val userRepository: UserRepository,
    private val timetableRepository: TimetableRepository,
) {
    suspend operator fun invoke(
        eventRequest: EventRequest
    ): Resource<String> {
        val userId = userRepository.userFlow.value.data?.id
        if (userId == null)
            return Resource.Error("user not found")

        return timetableRepository.addEvents(userId, eventRequest)
    }
}