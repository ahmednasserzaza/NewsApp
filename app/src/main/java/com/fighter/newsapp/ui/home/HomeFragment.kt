package com.fighter.newsapp.ui.home

import androidx.fragment.app.viewModels
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentHomeBinding
import com.fighter.newsapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
}