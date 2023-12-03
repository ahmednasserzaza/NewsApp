package com.fighter.newsapp.ui.search

import androidx.fragment.app.viewModels
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentSearchBinding
import com.fighter.newsapp.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
}