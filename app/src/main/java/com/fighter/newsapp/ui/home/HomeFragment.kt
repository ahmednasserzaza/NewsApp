package com.fighter.newsapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentHomeBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.fighter.newsapp.ui.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectNews()
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerHome.adapter = homeAdapter
    }

    private fun collectNews() {
        lifecycleScope.launch {
            viewModel.state.collect {
                homeAdapter.setItems(mutableListOf(it.egyptNews, it.latestNews))
            }
        }

    }

    private fun onCollectIntents(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnNavigateToNewsDetails -> TODO()
        }
    }
}