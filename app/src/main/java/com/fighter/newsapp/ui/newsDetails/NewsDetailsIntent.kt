package com.fighter.newsapp.ui.newsDetails

sealed class NewsDetailsIntent {
    data object OnNavigateBack : NewsDetailsIntent()
}