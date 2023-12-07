package com.fighter.newsapp.ui.shared

import com.fighter.newsapp.ui.base.BaseInteractionListener

interface NewsInteractionListener : BaseInteractionListener {
    fun onClickNewsItem(item: ArticleUiState)
    fun onClickBookMark(item: ArticleUiState)
}