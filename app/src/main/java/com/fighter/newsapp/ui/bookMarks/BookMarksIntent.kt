package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.ui.shared.ArticleUiState

sealed class BookMarksIntent {
    data class OnNavigateToNewsDetails(val article: ArticleUiState) : BookMarksIntent()
    data object OnRemoveArticleSuccess : BookMarksIntent()
}