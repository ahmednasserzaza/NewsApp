package com.fighter.newsapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentHomeBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.fighter.newsapp.ui.home.adapter.EgyptNewsAdapter
import com.fighter.newsapp.ui.home.adapter.LatestNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
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
        collectIntents(viewModel.intent)
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

    private fun collectIntents(intent: SharedFlow<HomeIntent>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                intent.collectLatest {
                    when (it) {
                        is HomeIntent.OnNavigateToNewsDetails -> Toast.makeText(
                            activity?.applicationContext,
                            it.articleTitle,
                            Toast.LENGTH_SHORT
                        ).show()

                        is HomeIntent.OnAddNewsToBookMarks -> Toast.makeText(
                            activity?.applicationContext,
                            it.article.publishedAt,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}