package com.fighter.newsapp.ui.search

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentSearchBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.fighter.newsapp.ui.home.adapter.LoadUIStateAdapter
import com.fighter.newsapp.ui.mapper.ArticleUiState
import com.fighter.newsapp.ui.utilities.collect
import com.fighter.newsapp.ui.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    private val searchAdapter by lazy { SearchAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val footerAdapter = LoadUIStateAdapter(searchAdapter::retry)
        binding.recyclerSearchNews.adapter = searchAdapter.withLoadStateFooter(footerAdapter)

        collect(
            flow = searchAdapter.loadStateFlow,
            action = { viewModel.setErrorUiState(it) }
        )

        collectLast(viewModel.state.value.news, ::setAllNews)
    }

    private suspend fun setAllNews(itemsPagingData: PagingData<ArticleUiState>) {
        searchAdapter.submitData(itemsPagingData)
    }
}