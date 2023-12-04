package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.mapper.ArticleUiState

sealed class HomeItem(val priority: Int) {
    data class TopSlider(val egyptNews: List<ArticleUiState>) : HomeItem(0)

    data class LatestNews(val latestNews: List<ArticleUiState>) : HomeItem(1)
}