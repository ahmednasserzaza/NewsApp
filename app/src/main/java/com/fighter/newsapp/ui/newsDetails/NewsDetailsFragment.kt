package com.fighter.newsapp.ui.newsDetails

import androidx.fragment.app.viewModels
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentNewsDetailsBinding
import com.fighter.newsapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_news_details
    override val viewModel: NewsDetailsViewModel by viewModels()
}