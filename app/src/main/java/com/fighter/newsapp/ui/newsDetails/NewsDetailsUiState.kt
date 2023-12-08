package com.fighter.newsapp.ui.newsDetails

import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState

data class NewsDetailsUiState(
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.NotFound,
    val isError: Boolean = false,
    val article: ArticleUiState = ArticleUiState(),
)