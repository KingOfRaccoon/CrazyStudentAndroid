package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.addevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.domain.entity.request.EventRequest
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.usecase.AddEventUseCase
import ru.kingofraccoons.crazystudent.domain.usecase.LoadUseCase
import ru.kingofraccoons.crazystudent.domain.util.Resource
import ru.kingofraccoons.crazystudent.data.util.DataTime

class AddEventViewModel(
    private val addEventUseCase: AddEventUseCase,
    private val loadUseCase: LoadUseCase,
    private val timetableRepository: TimetableRepository
) : ViewModel() {
    val name = MutableStateFlow("")
    val description = MutableStateFlow("")
    val address = MutableStateFlow("")
    val timeStart = MutableStateFlow<DataTime?>(null)
    val timeEnd = MutableStateFlow<DataTime?>(null)
    val addEventStatus = MutableStateFlow<Resource<String>>(Resource.Loading())

    fun addEvent() {
        viewModelScope.launch {
            addEventStatus.update {
                addEventUseCase(
                    EventRequest(
                        name.value,
                        description.value,
                        timeStart.value?.getDateAndTimeISO().orEmpty(),
                        timeEnd.value?.getDateAndTimeISO().orEmpty(),
                        address.value
                    )
                ).also {
                    if (it.isSuccess())
                        loadUseCase(timeStart.value?.getIsoFormat().orEmpty(), timetableRepository::loadTimetable)
                }
            }
        }
    }
}