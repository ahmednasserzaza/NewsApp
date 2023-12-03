package com.fighter.newsapp.ui.newsDetails

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class NewsDetailsViewModel :
    BaseViewModel<NewsDetailsUiState, NewsDetailsIntent>(NewsDetailsUiState.Idle) {
}