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
import com.fighter.newsapp.ui.home.HomeIntent
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.ErrorState
import com.fighter.newsapp.ui.shared.NewsInteractionListener
import com.fighter.newsapp.ui.shared.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    override fun onClickNewsItem(item: ArticleUiState) {
        sendNewIntent(SearchIntent.OnNavigateToNewsDetails(item.title))
    }

    override fun onClickBookMark(item: ArticleUiState) {
        sendNewIntent(SearchIntent.OnAddNewsToBookMarks(item))
    }

    fun setErrorUiState(combinedLoadStates: CombinedLoadStates, itemCount: Int) {
        when (combinedLoadStates.refresh) {
            is LoadState.Loading -> {
                updateState {
                    it.copy(isLoading = false, isError = false, itemCount = itemCount)
                }
            }

            is LoadState.Error -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        error = ErrorState.NoConnection,
                        itemCount = itemCount
                    )
                }
            }

            is LoadState.NotLoading -> {
                updateState {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        itemCount = itemCount
                    )
                }

            }
        }
    }

    fun resetUiStates() {
        updateState { SearchUiState() }
    }

    override fun getData() {
        getNews()
    }
}