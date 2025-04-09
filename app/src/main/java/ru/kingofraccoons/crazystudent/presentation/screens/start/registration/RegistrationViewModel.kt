package ru.kingofraccoons.crazystudent.presentation.screens.start.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.domain.entity.request.RegistrationUserRequest
import ru.kingofraccoons.crazystudent.domain.entity.response.timetable.FilterUnit
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.repository.UserRepository

class RegistrationViewModel(
    private val timetableRepository: TimetableRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val user = userRepository.userFlow
    val login = MutableStateFlow("")
    val password = MutableStateFlow("")
    val firstName = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    val address = MutableStateFlow("")
    val filterName = MutableStateFlow("")
    val filter = MutableStateFlow<FilterUnit?>(null)
    val filters =
        timetableRepository.groupsFlow.combine(timetableRepository.teachersFlow) { groups, teachers ->
            groups.data.orEmpty() + teachers.data.orEmpty()
        }

    fun updateFilter(filterUnit: FilterUnit) {
        filter.update { filterUnit }
    }

    init {
        viewModelScope.launch {
            timetableRepository.loadGroups()
        }

        viewModelScope.launch {
            timetableRepository.loadTeachers()
        }
    }

    fun registrationUser() {
        viewModelScope.launch {
            userRepository.registration(
                RegistrationUserRequest(
                    address.value,
                    firstName.value,
                    lastName.value,
                    (filter.value?.getFilterForServer() ?: ""),
                    login.value,
                    password.value
                )
            )
        }
    }
}