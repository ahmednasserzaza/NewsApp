package com.fighter.newsapp.ui.home.adapter

import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BaseAdapter
import com.fighter.newsapp.ui.mapper.ArticleUiState

class LatestNewsAdapter(
    items: List<ArticleUiState>,
    listener: NewsInteractionListener,
) : BaseAdapter<ArticleUiState>(items, listener) {
    override val layoutID: Int = R.layout.item_latest_news
}