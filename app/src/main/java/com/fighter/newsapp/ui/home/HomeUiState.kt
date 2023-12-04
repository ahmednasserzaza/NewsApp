package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.base.ErrorState

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.NotFound,
    val isError: Boolean = false,
    val egyptNews: HomeItem = HomeItem.TopSlider(emptyList()),
    val latestNews: HomeItem = HomeItem.LatestNews(emptyList()),
)