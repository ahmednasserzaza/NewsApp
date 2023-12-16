package com.fighter.newsapp.ui.newsDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentNewsDetailsBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_news_details
    override val viewModel: NewsDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectIntents(viewModel.intent)
    }

    private fun collectIntents(intent: SharedFlow<NewsDetailsIntent>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                intent.collectLatest { intent ->
                    when (intent) {
                        else -> {}
                    }
                }
            }
        }
    }
}