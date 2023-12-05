package com.fighter.newsapp.ui.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.fighter.newsapp.domain.entity.Article
import com.fighter.newsapp.domain.usecase.SearchNewsUseCase
import com.fighter.newsapp.ui.base.BaseViewModel
import com.fighter.newsapp.ui.base.ErrorState
import com.fighter.newsapp.ui.home.adapter.NewsInteractionListener
import com.fighter.newsapp.ui.mapper.ArticleUiState
import com.fighter.newsapp.ui.mapper.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNews: SearchNewsUseCase,
) : BaseViewModel<SearchUiState, SearchIntent>(SearchUiState()), NewsInteractionListener {

    fun onQueryTextChanged(searchTerm: CharSequence) {
        updateState { it.copy(searchQuery = searchTerm.toString()) }
        getNews()
    }

    private fun getNews() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            function = { searchNews.invoke(state.value.searchQuery) },
            onSuccess = ::onGetNewsSuccess,
            onError = ::onError
        )
    }

    private fun onGetNewsSuccess(newsDataFlow: Flow<PagingData<Article>>) {
        updateState {
            it.copy(
                isLoading = false,
                news = newsDataFlow.map { pagingData ->
                    pagingData.map { article ->
                        article.toUiState()
                    }
                }
            )
        }
    }

    private fun onError(errorState: ErrorState) {
        updateState { it.copy(isLoading = false, isError = true, error = errorState) }
    }

    override fun onClickNewsItem(newsTitle: String) {
        TODO("Not yet implemented")
    }

    override fun onClickBookMark(item: ArticleUiState) {
        TODO("Not yet implemented")
    }

    fun clearNews() {
        updateState { it.copy(isLoading = false, news = emptyFlow()) }
    }

    fun setErrorUiState(combinedLoadStates: CombinedLoadStates, itemCount: Int) {
        when (combinedLoadStates.refresh) {
            is LoadState.Loading -> {
                updateState {
                    it.copy(isLoading = true, isError = false)
                }
            }

            is LoadState.Error -> {
                updateState {
                    it.copy(isLoading = false, isError = true, error = ErrorState.NoConnection)
                }
            }

            is LoadState.NotLoading -> {
                if (itemCount < 1) {
                    updateState { it.copy(isLoading = false, isError = false) }
                }
            }
        }
    }
}