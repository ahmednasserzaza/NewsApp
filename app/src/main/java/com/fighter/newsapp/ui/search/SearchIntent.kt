package com.fighter.newsapp.ui.search

import com.fighter.newsapp.ui.home.HomeIntent
import com.fighter.newsapp.ui.shared.ArticleUiState

sealed class SearchIntent {
    data class OnSearchNews(val query: String) : SearchIntent()
    data class OnNavigateToNewsDetails(val articleTitle: String) : SearchIntent()
    data class OnAddNewsToBookMarks(val article: ArticleUiState) : SearchIntent()
}