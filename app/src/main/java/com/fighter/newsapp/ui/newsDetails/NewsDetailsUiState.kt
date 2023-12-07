package com.fighter.newsapp.ui.newsDetails

import com.fighter.newsapp.ui.shared.ErrorState

data class NewsDetailsUiState(
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.NotFound,
    val isError: Boolean = false,
    val imageUrl: String = "",
    val articleHeader: String = "",
    val articleContent: String = "",
)