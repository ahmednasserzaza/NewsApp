package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.base.ErrorState

sealed class HomeUiState {
    data object Idle : HomeUiState()
    data object Loading : HomeUiState()
    data class Error(val error: ErrorState) : HomeUiState()
}