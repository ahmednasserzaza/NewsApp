package com.fighter.newsapp.ui.newsDetails

import androidx.lifecycle.SavedStateHandle
import com.fighter.newsapp.domain.usecase.DeleteArticleUseCase
import com.fighter.newsapp.domain.usecase.SaveArticleUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val deleteArticle: DeleteArticleUseCase,
    private val saveArticle: SaveArticleUseCase,
) : BaseViewModel<NewsDetailsUiState, NewsDetailsIntent>(NewsDetailsUiState()) {

    private val articleArgs = NewsDetailsFragmentArgs.fromSavedStateHandle(state)

    init {
        getArticleDetails()
    }

    private fun getArticleDetails() {
        val article = articleArgs.article
        updateState { it.copy(article = article) }
    }

    private fun saveArticleToBookMarks(article: ArticleUiState) {
        tryToExecute(
            function = { saveArticle.invoke(article.toEntity()) },
            onSuccess = { onSaveArticleToBookmarksSuccess() },
            onError = ::onError
        )
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false) }
    }

    private fun onSaveArticleToBookmarksSuccess() {
        sendNewIntent(NewsDetailsIntent.OnAddArticleToBookmarks)
    }

    private fun deleteArticleFromBookmarks(article: ArticleUiState) {
        tryToExecute(
            function = { deleteArticle.invoke(article.toEntity()) },
            onSuccess = { onDeleteArticleSuccess() },
            onError = ::onError
        )
    }

    private fun onDeleteArticleSuccess() {
        sendNewIntent(NewsDetailsIntent.OnRemoveArticleFromBookmarks)
    }

    fun onClickBookmark(article: ArticleUiState) {
        if (state.value.article.isBookMarked) {
            deleteArticleFromBookmarks(article)
            updateState { it.copy(article = article.copy(isBookMarked = false)) }
        } else {
            saveArticleToBookMarks(article)
            updateState { it.copy(article = article.copy(isBookMarked = true)) }
        }
    }

    override fun getData() {
        getArticleDetails()
    }
}