package com.fighter.newsapp.ui.shared

import com.fighter.newsapp.ui.base.BaseInteractionListener
import com.fighter.newsapp.ui.shared.ArticleUiState

interface NewsInteractionListener : BaseInteractionListener {
    fun onClickNewsItem(newsTitle: String)
    fun onClickBookMark(item: ArticleUiState)
}