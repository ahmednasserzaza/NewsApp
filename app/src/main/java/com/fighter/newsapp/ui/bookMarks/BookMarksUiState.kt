package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.ui.base.ErrorState

sealed class BookMarksUiState {
    data object Idle : BookMarksUiState()
    data object Loading : BookMarksUiState()
    data class Error(val error: ErrorState) : BookMarksUiState()
}