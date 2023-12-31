package com.fighter.newsapp.ui.home

import androidx.lifecycle.viewModelScope
import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.usecase.GetEgyptNewsUseCase
import com.fighter.newsapp.domain.usecase.GetLatestNewsUseCase
import com.fighter.newsapp.domain.usecase.UpdateArticleBookmarkStatusUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.NewsInteractionListener
import com.fighter.newsapp.ui.shared.toEntity
import com.fighter.newsapp.ui.shared.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEgyptNews: GetEgyptNewsUseCase,
    private val getLatestNews: GetLatestNewsUseCase,
    private val updateArticle: UpdateArticleBookmarkStatusUseCase,
) : BaseViewModel<HomeUiState, HomeIntent>(HomeUiState()), NewsInteractionListener {

    init {
        getTrendingEgyptNews()
        getLatestBbcNews()
    }

    private fun getTrendingEgyptNews() {
        updateState { it.copy(isLoading = true) }
        tryToCollect(
            function = getEgyptNews::invoke,
            onSuccess = ::onGetEgyptNewsSuccess,
            onError = ::onError
        )
    }

    private fun onGetEgyptNewsSuccess(articles: List<Article>) {
        updateState { homeUiState ->
            homeUiState.copy(
                isLoading = false,
                egyptNews = articles.map { it.toUiState() }
            )
        }
    }

    private fun getLatestBbcNews() {
        updateState { it.copy(isLoading = true) }
        tryToCollect(
            function = getLatestNews::invoke,
            onSuccess = ::onGetLatestNewsSuccess,
            onError = ::onError
        )
    }

    private fun onGetLatestNewsSuccess(articles: List<Article>) {
        updateState { homeUiState ->
            homeUiState.copy(
                isLoading = false,
                latestNews = articles.map { it.toUiState() }
            )
        }
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
        updateState { it.copy(isLoading = false, isError = true, error = errorState) }
    }

    override fun onClickNewsItem(article: ArticleUiState) {
        sendNewIntent(HomeIntent.OnNavigateToNewsDetails(article))
    }

    override fun onClickBookMark(article: ArticleUiState) {
        updateBookmarkStatus(article)
    }

    override fun getData() {
        getTrendingEgyptNews()
        getLatestBbcNews()
    }

}