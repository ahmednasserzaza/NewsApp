package com.fighter.newsapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fighter.newsapp.domain.NewsAppException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<E>()
    val effect = _effect.asSharedFlow()

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (ErrorState) -> Unit,
    ): Job {
        return runWithErrorCheck(onError) {
            val result = function()
            onSuccess(result)
        }
    }

    private fun <T> runWithErrorCheck(
        onError: (ErrorState) -> Unit,
        action: suspend () -> T,
    ): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                action()
            } catch (exception: Exception) {
                if (exception is NewsAppException) handelApplicationExceptions(exception, onError)
                else onError(ErrorState.InvalidData)
            }
        }
    }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }

    protected fun sendNewEffect(newEffect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _effect.emit(newEffect)
        }
    }
}