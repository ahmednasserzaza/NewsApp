package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.usecase.GetBookmarkedArticlesUseCase
import com.fighter.newsapp.domain.usecase.UpdateArticleBookmarkStatusUseCase
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
    private val updateArticle: UpdateArticleBookmarkStatusUseCase,
    private val getBookmarkedArticles: GetBookmarkedArticlesUseCase,
) : BaseViewModel<BookMarkUiState, BookMarksIntent>(BookMarkUiState()), NewsInteractionListener {

    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        updateState { it.copy(isLoading = true) }
        tryToCollect(
            function = getBookmarkedArticles::invoke,
            onSuccess = ::onGetArticlesSuccess,
            onError = ::onError
        )
    }

    private fun onGetArticlesSuccess(articles: List<Article>) {
        updateState { it.copy(isLoading = false, articles = articles.toUiState()) }
    }

    private fun updateBookmarkStatus(article: ArticleUiState) {
        tryToExecute(
            function = { updateArticle.invoke(article.toEntity()) },
            onSuccess = { onUpdateBookmarkStatusSuccess() },
            onError = ::onError
        )
    }

    private fun onUpdateBookmarkStatusSuccess() {
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false, error = errorState, isError = true) }
    }

    override fun onClickNewsItem(article: ArticleUiState) {
        sendNewIntent(BookMarksIntent.OnNavigateToNewsDetails(article))
    }

    override fun onClickBookMark(article: ArticleUiState) {
        updateBookmarkStatus(article)
    }

    override fun getData() {
        getAllArticles()
    }
}