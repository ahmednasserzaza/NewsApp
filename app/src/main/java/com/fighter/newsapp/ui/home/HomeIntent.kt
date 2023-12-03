package com.fighter.newsapp.ui.home

sealed class HomeIntent {
    data class OnNavigateToNewsDetails(val id: String) : HomeIntent()
}