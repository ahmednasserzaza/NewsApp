package com.fighter.newsapp.ui.search

import com.fighter.newsapp.ui.shared.ArticleUiState

sealed class SearchIntent {
    data class OnNavigateToNewsDetails(val article: ArticleUiState) : SearchIntent()
}