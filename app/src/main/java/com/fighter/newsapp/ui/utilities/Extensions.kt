package com.fighter.newsapp.ui.utilities

import android.os.Build
import androidx.annotation.RequiresApi
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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

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

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDateString(): String {
    val instant: Instant = Instant.parse(this)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    val dayOfWeek = localDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val month = localDateTime.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val dayOfMonth = localDateTime.dayOfMonth
    val year = localDateTime.year

    return "$dayOfWeek, $month $dayOfMonth, $year"
}