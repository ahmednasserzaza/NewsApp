package com.fighter.newsapp.ui.bookMarks

sealed class BookMarksIntent {
    data class OnNavigateToNewsDetails(val id: String) : BookMarksIntent()

}