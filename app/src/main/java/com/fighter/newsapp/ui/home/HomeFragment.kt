package com.fighter.newsapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentHomeBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.fighter.newsapp.ui.home.adapter.EgyptNewsAdapter
import com.fighter.newsapp.ui.home.adapter.LatestNewsAdapter
import com.fighter.newsapp.ui.utilities.collect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private val topNewsAdapter by lazy {
        EgyptNewsAdapter(
            viewModel.state.value.egyptNews,
            viewModel
        )
    }
    private val latestNewsAdapter by lazy {
        LatestNewsAdapter(
            viewModel.state.value.latestNews,
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        collectNews()
    }

    private fun setAdapters() {
        binding.topSlider.recyclerSlider.adapter = topNewsAdapter
        binding.latestNews.recyclerLatestNews.adapter = latestNewsAdapter
    }

    private fun collectNews() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { homeUiState ->
                topNewsAdapter.setItems(homeUiState.egyptNews)
                latestNewsAdapter.setItems(homeUiState.latestNews)
            }
        }
    }

    private fun onCollectIntents(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnNavigateToNewsDetails -> TODO()
        }
    }
}