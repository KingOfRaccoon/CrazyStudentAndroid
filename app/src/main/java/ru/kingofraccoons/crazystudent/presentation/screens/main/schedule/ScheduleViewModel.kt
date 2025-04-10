package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.usecase.LoadUseCase
import ru.kingofraccoons.crazystudent.data.util.DataTime

class ScheduleViewModel(
    private val loadUseCase: LoadUseCase,
    private val timetableRepository: TimetableRepository
) : ViewModel() {
    val timetableFlow = timetableRepository.timetableFlow
    val dateFlow = MutableStateFlow(DataTime.now())

    init {
        loadTimetable()
    }

    fun loadTimetable(date: String = dateFlow.value.getIsoFormat()){
        viewModelScope.launch {
            loadUseCase(date, timetableRepository::loadTimetable)
        }
    }

    fun loadPlan(date: String = dateFlow.value.getIsoFormat()){
        viewModelScope.launch {
            loadUseCase(date, timetableRepository::loadPlan)
        }
    }
}