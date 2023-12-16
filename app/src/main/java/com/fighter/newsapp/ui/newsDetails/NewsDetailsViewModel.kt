package com.fighter.newsapp.ui.newsDetails

import androidx.lifecycle.SavedStateHandle
import com.fighter.newsapp.domain.usecase.UpdateArticleBookmarkStatusUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val updateArticle: UpdateArticleBookmarkStatusUseCase,
) : BaseViewModel<NewsDetailsUiState, NewsDetailsIntent>(NewsDetailsUiState()) {

    private val articleArgs = NewsDetailsFragmentArgs.fromSavedStateHandle(state)

    init {
        getArticleDetails()
    }

    private fun getArticleDetails() {
        val article = articleArgs.article
        updateState { it.copy(article = article) }
    }

    private fun updateBookmarkStatus(article: ArticleUiState) {
        tryToExecute(
            function = { updateArticle.invoke(article.toEntity()) },
            onSuccess = { onUpdateBookmarkStatusSuccess() },
            onError = ::onError
        )
    }

    private fun onUpdateBookmarkStatusSuccess() {
        updateState { it.copy(article = it.article.copy(isBookMarked = !state.value.article.isBookMarked)) }
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false) }
    }


    fun onClickBookmark(article: ArticleUiState) {
        updateBookmarkStatus(article)
    }

    override fun getData() {
        getArticleDetails()
    }
}