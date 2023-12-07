package com.fighter.newsapp.ui.newsDetails

import androidx.lifecycle.SavedStateHandle
import com.fighter.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
) : BaseViewModel<NewsDetailsUiState, NewsDetailsIntent>(NewsDetailsUiState()) {

    private val articleArgs = NewsDetailsFragmentArgs.fromSavedStateHandle(state)

    init {
        getArticleDetails()
    }

    private fun getArticleDetails() {
        val article = articleArgs.article
        updateState {
            it.copy(
                articleHeader = article.title,
                articleContent = article.content,
                imageUrl = article.imageUrl
            )
        }
    }

    override fun getData() {
        getArticleDetails()
    }
}