package ru.kingofraccoons.crazystudent.presentation.screens.main.schedule.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.kingofraccoons.crazystudent.data.util.DataTime
import ru.kingofraccoons.crazystudent.data.util.ListItemData
import ru.kingofraccoons.crazystudent.domain.entity.response.Route
import ru.kingofraccoons.crazystudent.domain.entity.response.RouteResponse
import ru.kingofraccoons.crazystudent.domain.repository.TimetableRepository
import ru.kingofraccoons.crazystudent.domain.usecase.LoadUseCase

class PlanViewModel(
    private val loadUseCase: LoadUseCase,
    private val timetableRepository: TimetableRepository,
) : ViewModel() {
    val selectedDayFlow = MutableStateFlow(DataTime.now())
    val planFlow = timetableRepository.planFlow

    fun loadPlan() {
        viewModelScope.launch(Dispatchers.IO) {
            loadUseCase(selectedDayFlow.value.getIsoFormat(), timetableRepository::loadPlan)
        }
    }

    fun transformRouteToListItems(route: RouteResponse): List<ListItemData> {
        val listItems = mutableListOf<ListItemData>()

        route.movements.forEachIndexed { index, movement ->
            val waypoint = movement.waypoint
            val label = waypoint.name // Основной заголовок — это имя точки

            // Формируем содержимое для разворачивания
            val content = buildString {
                append(waypoint.comment) // Комментарий, например, "пешком 510 м"

                // Если это остановка с транспортом (passage), добавляем информацию о маршрутах
                if (movement.type == "passage") {
                    movement.routes.let { routes ->
                        if (routes.isNotEmpty()) {
                            val route = routes[0]
                            append("\nТип транспорта: ${route.subtypeName}")
                            append("\nМаршрут: ${route.names.joinToString()}")
                        }
                    }
                    movement.metro?.let { metro ->
                        append("\nЛиния метро: ${metro.lineName}")
                        append("\nНаправление: ${metro.uiDirectionSuggest}")

                        append("\n${metro.uiStationCount}")
                        if (metro.exitComment.isNotEmpty()) {
                            append("\nВыход: ${metro.exitComment}")
                        }
                    }
                    movement.platforms?.let { platforms ->
                        if (platforms.names.isNotEmpty())
                            append("\nОстановки: ${platforms.names.joinToString(" → ")}")
                    }
                }
            }

            // Добавляем элемент в список
            listItems.add(
                ListItemData(
                    label = label,
                    content = content.trim()
                )
            )
        }

        return listItems
    }
}