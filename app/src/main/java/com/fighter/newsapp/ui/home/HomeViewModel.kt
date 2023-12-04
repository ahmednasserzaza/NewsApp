package com.fighter.newsapp.ui.home

import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.usecase.GetEgyptNewsUseCase
import com.fighter.newsapp.domain.usecase.GetLatestNewsUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.base.ErrorState
import com.fighter.newsapp.ui.home.adapter.NewsInteractionListener
import com.fighter.newsapp.ui.mapper.ArticleUiState
import com.fighter.newsapp.ui.mapper.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEgyptNews: GetEgyptNewsUseCase,
    private val getLatestNews: GetLatestNewsUseCase,
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
        println("Egypt news success = $articles")
        updateState { homeUiState ->
            homeUiState.copy(
                isLoading = false,
                egyptNews = HomeItem.TopSlider(articles.map { it.toUiState() })
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
                latestNews = HomeItem.LatestNews(articles.map { it.toUiState() })
            )
        }
    }

    private fun onError(errorState: ErrorState) {
        println("Egypt news Error = $errorState")
        updateState { it.copy(isLoading = false, isError = true, error = errorState) }
    }

    override fun onClickNewsItem(newsTitle: String) {
        TODO("Not yet implemented")
    }

    override fun onClickBookMark(item: ArticleUiState) {
        TODO("Not yet implemented")
    }

}