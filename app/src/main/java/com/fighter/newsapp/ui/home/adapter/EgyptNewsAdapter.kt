package com.fighter.newsapp.ui.home.adapter

import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BaseAdapter
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.NewsInteractionListener

class EgyptNewsAdapter(
    items: List<ArticleUiState>,
    listener: NewsInteractionListener,
) : BaseAdapter<ArticleUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_top_slider
}