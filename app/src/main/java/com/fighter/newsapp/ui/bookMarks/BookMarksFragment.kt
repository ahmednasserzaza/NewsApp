package com.fighter.newsapp.ui.bookMarks

import androidx.fragment.app.viewModels
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentBookMarksBinding
import com.fighter.newsapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookMarksFragment : BaseFragment<FragmentBookMarksBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_book_marks
    override val viewModel: BookMarksViewModel by viewModels()
}