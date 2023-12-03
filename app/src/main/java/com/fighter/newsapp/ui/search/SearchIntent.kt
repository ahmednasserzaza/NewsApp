package com.fighter.newsapp.ui.search

sealed class SearchIntent {
    data class OnSearchNews(val query: String) : SearchIntent()
}