package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class BookMarksViewModel : BaseViewModel<BookMarksUiState, BookMarksIntent>(BookMarksUiState.Idle) {
}