package com.fighter.newsapp.ui.bookMarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fighter.newsapp.R
import com.fighter.newsapp.databinding.FragmentBookMarksBinding
import com.fighter.newsapp.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookMarksFragment : BaseFragment<FragmentBookMarksBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_book_marks
    override val viewModel: BookMarksViewModel by viewModels()
    private val bookmarksAdapter by lazy {
        BookmarksAdapter(
            viewModel.state.value.articles,
            viewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerBookmarks.adapter = bookmarksAdapter
        collectSavedArticles()
    }

    private fun collectSavedArticles() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { bookmarksUiState ->
                    bookmarksAdapter.setItems(bookmarksUiState.articles)
                }
            }
        }
    }

    private fun collectIntents(intent: SharedFlow<BookMarksIntent>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                intent.collectLatest { bookmarkIntent ->
                    when (bookmarkIntent) {
                        is BookMarksIntent.OnNavigateToNewsDetails -> {

                        }

                        BookMarksIntent.OnRemoveArticleSuccess -> {
                            Snackbar.make(
                                binding.root,
                                "Removed Successfully",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}