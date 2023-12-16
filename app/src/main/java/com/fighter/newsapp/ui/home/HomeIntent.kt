package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.shared.ArticleUiState

sealed class HomeIntent {
    data class OnNavigateToNewsDetails(val article: ArticleUiState) : HomeIntent()
//    data object OnRemoveArticleFromBookmarks : HomeIntent()
//    data object OnAddArticleToBookmarks : HomeIntent()
}