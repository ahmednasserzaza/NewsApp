package com.fighter.newsapp.ui.newsDetails

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor() :
    BaseViewModel<NewsDetailsUiState, NewsDetailsIntent>(NewsDetailsUiState.Idle) {
}