package com.fighter.newsapp.ui.search

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SearchViewModel : BaseViewModel<SearchUiState,SearchIntent>(SearchUiState.Idle) {
}