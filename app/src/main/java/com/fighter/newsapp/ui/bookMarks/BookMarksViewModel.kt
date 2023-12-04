package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookMarksViewModel @Inject constructor() :
    BaseViewModel<BookMarksUiState, BookMarksIntent>(BookMarksUiState.Idle) {
}