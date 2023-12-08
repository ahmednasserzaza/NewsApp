package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.usecase.DeleteArticleUseCase
import com.fighter.newsapp.domain.usecase.GetArticlesUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.NewsInteractionListener
import com.fighter.newsapp.ui.shared.toEntity
import com.fighter.newsapp.ui.shared.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookMarksViewModel @Inject constructor(
    private val deleteArticle: DeleteArticleUseCase,
    private val getArticles: GetArticlesUseCase,
) : BaseViewModel<BookMarkUiState, BookMarksIntent>(BookMarkUiState()), NewsInteractionListener {

    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        updateState { it.copy(isLoading = true) }
        tryToCollect(
            function = getArticles::invoke,
            onSuccess = ::onGetArticlesSuccess,
            onError = ::onError
        )
    }

    private fun onGetArticlesSuccess(articles: List<Article>) {
        updateState { it.copy(isLoading = false, articles = articles.toUiState()) }
    }

    private fun deleteArticle(article: ArticleUiState) {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            function = { deleteArticle.invoke(article.toEntity()) },
            onSuccess = { onDeleteArticleSuccess() },
            onError = ::onError
        )
    }

    private fun onDeleteArticleSuccess() {
        updateState { it.copy(isLoading = false) }
        sendNewIntent(BookMarksIntent.OnRemoveArticleSuccess)
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false, error = errorState, isError = true) }
    }

    override fun onClickNewsItem(article: ArticleUiState) {
        sendNewIntent(BookMarksIntent.OnNavigateToNewsDetails(article))
    }

    override fun onClickBookMark(article: ArticleUiState) {
        deleteArticle(article)
    }

    override fun getData() {
        getAllArticles()
    }

}