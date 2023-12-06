package com.fighter.newsapp.ui.search

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentSearchBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.fighter.newsapp.ui.search.adapter.LoadUIStateAdapter
import com.fighter.newsapp.ui.search.adapter.SearchAdapter
import com.fighter.newsapp.ui.utilities.collect
import com.fighter.newsapp.ui.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    private val searchAdapter by lazy { SearchAdapter(viewModel) }
    private val oldValue = MutableStateFlow(SearchUiState())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSearchResultsBySearchTerm()
    }

    @OptIn(FlowPreview::class)
    private fun getSearchResultsBySearchTerm() {
        lifecycleScope.launch {
            viewModel.state.debounce(1000).collectLatest { searchUiState ->
                if (searchUiState.searchQuery.trim().isNotEmpty()
                    && (oldValue.value.searchQuery != viewModel.state.value.searchQuery)
                ) {
                    bindNews()
                    oldValue.emit(viewModel.state.value)
                } else if (searchUiState.searchQuery.trim().isEmpty()) {
                    searchAdapter.submitData(PagingData.empty())
                    oldValue.emit(SearchUiState())
                }
            }
        }
    }

    private fun bindNews() {
        val footerAdapter = LoadUIStateAdapter(searchAdapter::retry)
        binding.recyclerSearchNews.adapter = searchAdapter.withLoadStateFooter(footerAdapter)

        collect(
            flow = searchAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it, searchAdapter.itemCount) }
        )
        getNewsSearchResults()
    }

    private fun getNewsSearchResults() {
        collectLast(viewModel.state.value.news) { searchAdapter.submitData(it) }
    }

}