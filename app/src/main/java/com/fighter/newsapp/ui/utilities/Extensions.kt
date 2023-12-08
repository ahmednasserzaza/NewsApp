package com.fighter.newsapp.ui.utilities

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(action)
        }
    }
}

fun <T> LifecycleOwner.collect(flow: Flow<T>, action: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                action.invoke(it)
            }
        }
    }
}

fun Fragment.findNavControllerSafely(id: Int): NavController? {
    return if (findNavController().currentDestination?.id == id) {
        findNavController()
    } else {
        null
    }
}

fun formatCreationDate(apiDateString: String): String {
    val apiDateInstant = Instant.parse(apiDateString)
    return apiDateInstant.toLocalDateTime(TimeZone.UTC).run {
        "${dayOfWeek.name.lowercase()}, ${month.name.lowercase()} $dayOfMonth, $year"
    }
}

fun formatCreationDate(date: LocalDateTime): String {
    val apiDateInstant = date.toInstant(TimeZone.UTC)
    return apiDateInstant.toLocalDateTime(TimeZone.UTC).run {
        "${dayOfWeek.name.lowercase()}, ${month.name.lowercase()} $dayOfMonth, $year"
    }
}