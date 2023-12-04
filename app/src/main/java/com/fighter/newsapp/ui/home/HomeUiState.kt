package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.base.ErrorState
import com.fighter.newsapp.ui.mapper.ArticleUiState

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.NotFound,
    val isError: Boolean = false,
    val egyptNews: List<ArticleUiState> = emptyList(),
    val latestNews: List<ArticleUiState> = emptyList(),
)