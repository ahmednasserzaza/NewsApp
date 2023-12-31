package com.fighter.newsapp.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BasePagingAdapter
import com.fighter.newsapp.ui.shared.NewsInteractionListener
import com.fighter.newsapp.ui.shared.ArticleUiState

class SearchAdapter(
    listener: NewsInteractionListener,
) : BasePagingAdapter<ArticleUiState>(NewsComparator, listener) {
    override val layoutID: Int = R.layout.item_latest_news

    object NewsComparator : DiffUtil.ItemCallback<ArticleUiState>() {
        override fun areItemsTheSame(oldItem: ArticleUiState, newItem: ArticleUiState): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ArticleUiState,
            newItem: ArticleUiState,
        ): Boolean {
            return oldItem == newItem
        }
    }
}