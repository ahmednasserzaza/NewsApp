package com.fighter.newsapp.ui.newsDetails

import com.fighter.newsapp.ui.shared.ErrorState

sealed class NewsDetailsUiState {
    data object Idle : NewsDetailsUiState()
    data object Loading : NewsDetailsUiState()
    data class Error(val error: ErrorState) : NewsDetailsUiState()
}