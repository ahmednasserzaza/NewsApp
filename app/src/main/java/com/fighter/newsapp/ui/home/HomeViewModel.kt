package com.fighter.newsapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.usecase.DeleteArticleUseCase
import com.fighter.newsapp.domain.usecase.DoesArticleBookmarkedUseCase
import com.fighter.newsapp.domain.usecase.GetArticlesUseCase
import com.fighter.newsapp.domain.usecase.GetEgyptNewsUseCase
import com.fighter.newsapp.domain.usecase.GetLatestNewsUseCase
import com.fighter.newsapp.domain.usecase.SaveArticleUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.NewsInteractionListener
import com.fighter.newsapp.ui.shared.toEntity
import com.fighter.newsapp.ui.shared.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEgyptNews: GetEgyptNewsUseCase,
    private val getLatestNews: GetLatestNewsUseCase,
    private val deleteArticle: DeleteArticleUseCase,
    private val saveArticle: SaveArticleUseCase,
    private val isArticleBookmarked: DoesArticleBookmarkedUseCase,
) : BaseViewModel<HomeUiState, HomeIntent>(HomeUiState()), NewsInteractionListener {

    init {
        getTrendingEgyptNews()
        getLatestBbcNews()
    }

    private fun getTrendingEgyptNews() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
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
        tryToExecute(
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

    private fun saveArticleToBookMarks(article: ArticleUiState) {
        tryToExecute(
            function = { saveArticle.invoke(article.toEntity()) },
            onSuccess = { onSaveArticleToBookmarksSuccess() },
            onError = ::onError
        )
    }

    private fun onSaveArticleToBookmarksSuccess() {
        sendNewIntent(HomeIntent.OnAddArticleToBookmarks)
    }

    private fun deleteArticleFromBookmarks(article: ArticleUiState) {
        tryToExecute(
            function = { deleteArticle.invoke(article.toEntity()) },
            onSuccess = { onDeleteArticleSuccess() },
            onError = ::onError
        )
    }

    private fun onDeleteArticleSuccess() {
        sendNewIntent(HomeIntent.OnRemoveArticleFromBookmarks)
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false, isError = true, error = errorState) }
    }

    override fun onClickNewsItem(article: ArticleUiState) {
        sendNewIntent(HomeIntent.OnNavigateToNewsDetails(article))
    }

    private fun updateArticleBookmark(article: ArticleUiState, isBookmarked: Boolean) {
        updateState { currentState ->
            val updatedEgyptNews = updateArticleList(currentState.egyptNews, article, isBookmarked)
            val updatedLatestNews =
                updateArticleList(currentState.latestNews, article, isBookmarked)

            currentState.copy(
                egyptNews = updatedEgyptNews,
                latestNews = updatedLatestNews
            )
        }
    }

    private fun updateArticleList(
        articleList: List<ArticleUiState>,
        updatedArticle: ArticleUiState,
        isBookmarked: Boolean,
    ): List<ArticleUiState> {
        return articleList.map { article ->
            if (isSameArticle(article, updatedArticle)) {
                article.copy(isBookMarked = isBookmarked)
            } else {
                article
            }
        }
    }

    private fun isSameArticle(article1: ArticleUiState, article2: ArticleUiState): Boolean {
        return article1.title == article2.title
    }

    override fun onClickBookMark(article: ArticleUiState) {
        viewModelScope.launch {
            val isBookmarked = isArticleBookmarked.invoke(article.title)
            if (isBookmarked) {
                deleteArticleFromBookmarks(article)
                updateArticleBookmark(article, false)
            } else {
                saveArticleToBookMarks(article)
                updateArticleBookmark(article, true)
            }
        }
    }

    override fun getData() {
        getTrendingEgyptNews()
        getLatestBbcNews()
    }

}