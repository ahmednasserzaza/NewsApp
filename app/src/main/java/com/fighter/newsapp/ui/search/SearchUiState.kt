package com.fighter.newsapp.ui.search

import com.fighter.newsapp.ui.base.ErrorState

sealed class SearchUiState {
    data object Idle : SearchUiState()
    data object Loading : SearchUiState()
    data class Error(val error: ErrorState) : SearchUiState()
}