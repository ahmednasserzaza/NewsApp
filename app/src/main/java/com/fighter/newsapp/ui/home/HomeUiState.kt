package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.base.ErrorState
import com.fighter.newsapp.ui.mapper.ArticleUiState

sealed class HomeUiState {
    data object Idle : HomeUiState()
    data object Loading : HomeUiState()
    data class Error(val error: ErrorState) : HomeUiState()
    data class SliderNewsUiState(val egyptNews: List<ArticleUiState>) : HomeUiState()
    data class LatestNewsUiState(val latestNews: List<ArticleUiState>) : HomeUiState()
}