package com.fighter.newsapp.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BasePagingAdapter
import com.fighter.newsapp.ui.mapper.ArticleUiState

class EgyptNewsAdapter(
    items: List<ArticleUiState>,
    listener: NewsInteractionListener,
) : BasePagingAdapter<ArticleUiState>(MediaComparator, listener) {
    override val layoutID: Int = R.layout.item_top_slider


    object MediaComparator : DiffUtil.ItemCallback<ArticleUiState>() {
        override fun areItemsTheSame(oldItem: ArticleUiState, newItem: ArticleUiState) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ArticleUiState, newItem: ArticleUiState) =
            oldItem == newItem
    }
}