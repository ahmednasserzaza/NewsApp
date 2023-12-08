package com.fighter.newsapp.ui.shared

import com.fighter.newsapp.ui.base.BaseInteractionListener

interface NewsInteractionListener : BaseInteractionListener {
    fun onClickNewsItem(article: ArticleUiState)
    fun onClickBookMark(article: ArticleUiState)
}