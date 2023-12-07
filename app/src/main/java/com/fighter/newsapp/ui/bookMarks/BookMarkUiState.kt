package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState

data class BookMarkUiState(
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.NotFound,
    val isError: Boolean = false,
    val articles: List<ArticleUiState> = emptyList(),
)