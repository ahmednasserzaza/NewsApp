package com.fighter.newsapp.ui.bookMarks

import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BaseAdapter
import com.fighter.newsapp.ui.shared.ArticleUiState
import com.fighter.newsapp.ui.shared.NewsInteractionListener

class BookmarksAdapter(
    articles: List<ArticleUiState>,
    listener: NewsInteractionListener,
) : BaseAdapter<ArticleUiState>(articles, listener) {
    override val layoutID: Int = R.layout.item_latest_news
}