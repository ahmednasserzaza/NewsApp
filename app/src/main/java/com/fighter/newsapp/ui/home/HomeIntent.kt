package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.shared.ArticleUiState

sealed class HomeIntent {
    data class OnNavigateToNewsDetails(val article: ArticleUiState) : HomeIntent()
    data class OnAddNewsToBookMarks(val article: ArticleUiState) : HomeIntent()
}