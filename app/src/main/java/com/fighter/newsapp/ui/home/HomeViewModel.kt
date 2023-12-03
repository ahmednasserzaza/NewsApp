package com.fighter.newsapp.ui.home

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel : BaseViewModel<HomeUiState, HomeIntent>(HomeUiState.Idle) {

}