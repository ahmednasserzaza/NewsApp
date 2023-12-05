package com.fighter.newsapp.ui.search

import androidx.paging.PagingData
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.ArticleUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchUiState(
    val isLoading: Boolean = false,
    val isPageLoading:Boolean = false,
    val error: ErrorState = ErrorState.InvalidData,
    val isError: Boolean = false,
    val news: Flow<PagingData<ArticleUiState>> = emptyFlow(),
    val searchQuery: String = "",
)