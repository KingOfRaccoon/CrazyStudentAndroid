package ru.kingofraccoons.crazystudent.domain.usecase

import ru.kingofraccoons.crazystudent.domain.repository.UserRepository
import ru.kingofraccoons.crazystudent.domain.util.Resource

class LoadUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        date: String,
        loadFunction: suspend (Long, String) -> Unit,
    ): Resource<String> {
        val userId = userRepository.userFlow.value.data?.id
        if (userId == null)
            return Resource.Error("user not found")

        loadFunction(userId, date)
        return Resource.Success("")
    }
}