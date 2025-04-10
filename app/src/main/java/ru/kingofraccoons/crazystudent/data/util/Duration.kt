package ru.kingofraccoons.crazystudent.data.util

import kotlinx.serialization.Serializable

@Serializable
data class Duration(val minutes: Long) {
    fun plus(other: Duration): Duration = Duration(this.minutes + other.minutes)
    fun minus(other: Duration): Duration = Duration(this.minutes - other.minutes)
    fun toHoursAndMinutes(): Pair<Long, Long> {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return hours to remainingMinutes
    }

    override fun toString(): String {
        val (hours, minutes) = toHoursAndMinutes()
        return "${hours}h ${minutes}m"
    }

    operator fun compareTo(other: Duration): Int {
        return when {
            this.minutes > other.minutes -> 1
            this.minutes < other.minutes -> -1
            else -> 0
        }
    }
}

fun calculateDifference(start: DataTime, end: DataTime): Duration {
    val startSeconds = start.getTimeInMilliSeconds()
    val endSeconds = end.getTimeInMilliSeconds()
    val minutes = (endSeconds - startSeconds) / 60
    return Duration(minutes)
}