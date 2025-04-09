package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metro(
    @SerialName("boarding_parts_suggest")
    val boardingPartsSuggest: List<Int>,
    @SerialName("color")
    val color: String,
    @SerialName("exit_comment")
    val exitComment: String,
    @SerialName("exit_entrance_number")
    val exitEntranceNumber: String,
    @SerialName("line_name")
    val lineName: String,
    @SerialName("ui_direction_suggest")
    val uiDirectionSuggest: String,
    @SerialName("ui_station_count")
    val uiStationCount: String = ""
)