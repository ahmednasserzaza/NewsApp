package com.fighter.newsapp.ui.search

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel<SearchUiState,SearchIntent>(SearchUiState.Idle) {
}