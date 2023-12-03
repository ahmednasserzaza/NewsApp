package com.fighter.newsapp.domain.utility

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

fun String.toDateTimeFormat(): String {
    val currentInstant: Instant = Clock.System.now()
    val inputLocalDateTime: LocalDateTime = LocalDateTime.parse(this)

    val differenceInHours: Long =
        currentInstant.minus(inputLocalDateTime.toInstant(TimeZone.UTC)).inWholeHours

    return if (differenceInHours in 1..24) {
        "$differenceInHours hours ago"
    } else {
        inputLocalDateTime.dayOfWeek.run {
            val dayOfWeekString = this.toString()
            val monthString = inputLocalDateTime.month.toString()
            val dayOfMonth = inputLocalDateTime.dayOfMonth
            val year = inputLocalDateTime.year
            "$dayOfWeekString, $monthString $dayOfMonth, $year"
        }
    }
}