package com.fighter.newsapp.ui.newsDetails

import com.fighter.newsapp.ui.home.HomeIntent

sealed class NewsDetailsIntent {
    data object OnRemoveArticleFromBookmarks : NewsDetailsIntent()
    data object OnAddArticleToBookmarks : NewsDetailsIntent()
}