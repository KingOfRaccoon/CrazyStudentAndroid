package ru.kingofraccoons.crazystudent.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.data.source.TimetableService
import ru.skittens.prostoleti.data.util.DataTime

class MainViewModel(private val timetableService: TimetableService) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    val currentDay = MutableStateFlow(DataTime.now().getIsoFormat())

    fun loadDate(date: String, groupNumber: String = "1101") {
        coroutineScope.launch {
//            _timetableMapFlow.update {
//                it.plus(date to timetableService.getTimetable(date, groupNumber))
//            }
        }
    }

    init {
        loadVacancies()
    }

    fun loadVacancies() {
        coroutineScope.launch {
//            _vacanciesFlow.update { timetableService.getVacancy(listOf("гос.задание")) }
        }
    }

//    fun getTimetable() = combine(timetableMapFlow, currentDay) { timetable, day ->
//        timetable[day]
//    }
}